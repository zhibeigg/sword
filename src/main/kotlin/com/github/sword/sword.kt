package com.github.sword

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.configuration.Configuration
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.module.configuration.Config

object sword : Plugin() {

    @Config("config.yml")
    lateinit var config: Configuration

    override fun onEnable() {

        config.getConfigurationSection("1")
        info("Sword!启动成功！by.zhi_bei")
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