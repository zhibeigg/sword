package com.github.sword

import com.germ.germplugin.api.GermKeyAPI
import com.germ.germplugin.api.KeyType
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.warning
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
        GermKeyAPI.registerKey(KeyType.KEY_R)
        say("&6萌芽按键注册成功")
        enable("GermPlugin")
        enable("Chemdah")
        enable("Adyeshach")
        enable("Planners")
        enable("OriginAttribute")
        enable("BattlePass")
        enable("CMI")
        enable("PlaceholderAPI")
        enable("AuthMe")
        enable("Kingdoms")
        enable("MyPet")
        enable("MiraiMC")
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

    fun debug(s: String?) {
        if (!config.getBoolean("debug")) return
        warning(s)
    }

    fun enable(s: String) {
        if (Bukkit.getServer().pluginManager.getPlugin(s)?.isEnabled == true) {
            say("&b已开启附属 &e${s}")
            return
        } else {
            say("&b未查找到附属 &e${s}")
            return
        }
    }

}