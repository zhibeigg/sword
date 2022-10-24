package com.github.sword.game

import com.bh.planners.api.PlannersAPI
import com.github.sword.sword
import com.github.sword.sword.debug
import ink.ptms.adyeshach.api.AdyeshachAPI
import ink.ptms.adyeshach.api.event.AdyeshachEntityInteractEvent
import ink.ptms.chemdah.api.ChemdahAPI
import ink.ptms.chemdah.api.ChemdahAPI.conversation
import ink.ptms.chemdah.core.conversation.Trigger
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.onlinePlayers
import taboolib.common.util.asList
import taboolib.expansion.createHelper

@CommandHeader("sword", aliases = ["swo", "sw"], permission = "sword.command")
object Command {

    @CommandBody
    val helper = mainCommand {
        createHelper()
    }

    @CommandBody
    val reload = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            sword.config.reload()
            sender.sendMessage("重载成功.")
        }
    }

    @CommandBody
    val open = subCommand {
        dynamic(commit = "player") {
            suggestion<CommandSender> { _, _ -> onlinePlayers().map { it.name } }
            dynamic(commit = "entity") {
                execute<CommandSender> { _, context, argument ->
                    val player = Bukkit.getPlayerExact(context.argument(-1)) ?: return@execute
                    val entity = AdyeshachAPI.getEntityFromId(argument, player) ?: return@execute
                    debug("打开，实体$entity,玩家$player")
                    AdyeshachEntityInteractEvent(entity, player, true, player.velocity).call()
                }
            }
        }
    }
}
