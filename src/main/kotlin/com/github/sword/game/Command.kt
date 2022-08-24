package com.github.sword.game

import com.github.sword.sword
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper

@CommandHeader("sword", aliases = ["swo", "sw"])
object Command {

    @CommandBody
    val helper = mainCommand {
        createHelper()
    }

    @CommandBody
    val reload = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            sword.config.reload()
            sender.sendMessage("reload successful.")
        }
    }
}
