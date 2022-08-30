package com.github.sword

import com.germ.germplugin.api.GermKeyAPI
import com.germ.germplugin.api.KeyType
import com.germ.germplugin.api.yaml.YamlManager
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import taboolib.platform.BukkitPlugin

object sword : Plugin() {

    @Config("config.yml", migrate = true, autoReload = true)
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
        GermKeyAPI.registerKey(KeyType.KEY_R)
    }

    override fun onDisable() {
        say("&6Sword!&a卸载成功！&cby.zhi_bei")
    }
    fun parse(s: String): String {
        return s.replace("&", "§").replace("§§", "&")
    }
    fun say(s: String?) {
        val sender: CommandSender = Bukkit.getConsoleSender()
        sender.sendMessage(s?.let { parse(it) })
    }
}