package com.github.sword

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration

object sword : Plugin() {

    @Config("config.yml", migrate = true)
    lateinit var config: Configuration

    override fun onEnable() {
        say("&a┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓")
        say("&a┃&e      ___           ___           ___           ___           ___     &a┃")
        say("&a┃&e     /\\  \\         /\\__\\         /\\  \\         /\\  \\         /\\  \\    &a┃")
        say("&a┃&e    /::\\  \\       /:/ _/_       /::\\  \\       /::\\  \\       /::\\  \\   &a┃")
        say("&a┃&e   /:/\\ \\  \\     /:/ /\\__\\     /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\  &a┃")
        say("&a┃&e  _\\:\\~\\ \\  \\   /:/ /:/ _/_   /:/  \\:\\  \\   /::\\~\\:\\  \\   /:/  \\:\\__\\ &a┃")
        say("&a┃&e /\\ \\:\\ \\ \\__\\ /:/_/:/ /\\__\\ /:/__/ \\:\\__\\ /:/\\:\\ \\:\\__\\ /:/__/ \\:|__|&a┃")
        say("&a┃&e \\:\\ \\:\\ \\/__/ \\:\\/:/ /:/  / \\:\\  \\ /:/  / \\/_|::\\/:/  / \\:\\  \\ /:/  /&a┃")
        say("&a┃&e  \\:\\ \\:\\__\\    \\::/_/:/  /   \\:\\  /:/  /     |:|::/  /   \\:\\  /:/  / &a┃")
        say("&a┃&e   \\:\\/:/  /     \\:\\/:/  /     \\:\\/:/  /      |:|\\/__/     \\:\\/:/  /  &a┃")
        say("&a┃&e    \\::/  /       \\::/  /       \\::/  /       |:|  |        \\::/__/   &a┃")
        say("&a┃&e     \\/__/         \\/__/         \\/__/         \\|__|         ~~       &a┃")
        say("&a┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛")
        say("&6Sword!&a启动成功！&cby.zhi_bei")
    }

    override fun onDisable() {
        info("Sword!卸载成功！by.zhi_bei")
    }

    fun say(s: String?) {
        s!!.replace("&", "§")
        val sender: CommandSender = Bukkit.getConsoleSender()
        sender.sendMessage(s)
    }
}