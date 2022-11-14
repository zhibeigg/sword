package com.github.sword.game

import com.github.sword.sword
import com.github.sword.sword.debug
import ink.ptms.adyeshach.api.AdyeshachAPI
import ink.ptms.adyeshach.api.event.AdyeshachEntityInteractEvent
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.kingdoms.constants.player.KingdomPlayer
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.onlinePlayers
import taboolib.expansion.createHelper

@CommandHeader("sword", aliases = ["swo", "sw"], permission = "sword.command")
object command {

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
        dynamic("player") {
            suggestion<CommandSender> { _, _ -> onlinePlayers().map { it.name } }
            dynamic("entity") {
                execute<CommandSender> { _, context, argument ->
                    val player = Bukkit.getPlayerExact(context.argument(-1)) ?: return@execute
                    val entity = AdyeshachAPI.getEntityFromId(argument, player) ?: return@execute
                    debug("打开，实体$entity,玩家$player")
                    AdyeshachEntityInteractEvent(entity, player, true, player.velocity).call()
                }
            }
        }
    }

    @CommandBody
    val add = subCommand {
        dynamic("player") {
            suggestion<CommandSender> { _, _ -> Bukkit.getOnlinePlayers().map { it.displayName }; }
            dynamic("number") {
                suggestion<CommandSender> { _, _ -> listOf("1"); }
                execute<CommandSender> { _, context, argument ->
                    val player = Bukkit.getPlayerExact(context.argument(-1)) ?: return@execute
                    val kingdom = KingdomPlayer.getKingdomPlayer(player).kingdom ?: return@execute
                    val number = argument.toInt()
                    debug("国家$kingdom,土地加$number,玩家$player")
                    kingdom.maxLandsModifier += number
                }
            }
        }
    }

}
