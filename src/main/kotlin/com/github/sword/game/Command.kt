package com.github.sword.game

import com.germ.germplugin.api.dynamic.gui.GermGuiScreen
import com.github.sword.sword
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper
import taboolib.platform.util.onlinePlayers

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
            onlinePlayers.forEach(){
                GermGuiScreen.getGermGuiScreen("quest${it.name}").openGui(it)
        }
            sender.sendMessage("重载成功.")
        }
    }
}
