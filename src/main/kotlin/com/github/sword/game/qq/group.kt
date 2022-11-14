package com.github.sword.game.qq

import com.github.sword.sword.config
import com.github.sword.sword.debug
import me.clip.placeholderapi.PlaceholderAPI
import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.bukkit.event.bot.MiraiBotOnlineEvent
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiGroupMessageEvent
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getOnlinePlayers
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.server.PluginDisableEvent
import taboolib.common.platform.event.SubscribeEvent

object group {

    var bot : MiraiBot? = null

    @SubscribeEvent
    fun e(e: MiraiBotOnlineEvent) {
        bot = e.bot
    }

    @SubscribeEvent
    fun join(e: PlayerJoinEvent) {
        if (!config.getBoolean("qq-join")) return
        val name = e.player.displayName
        if (bot == null) return
        bot!!.getGroup(config.getLong("qq")).sendMessage(config.getString("join")?.replace("%player%", name))
    }

    @SubscribeEvent
    fun quit(e: PlayerQuitEvent) {
        if (!config.getBoolean("qq-quit")) return
        val name = e.player.displayName
        if (bot == null) return
        bot!!.getGroup(config.getLong("qq")).sendMessage(config.getString("quit")?.replace("%player%", name))
    }

    @SubscribeEvent
    fun getmessage(e: MiraiGroupMessageEvent) {
        if (!config.getBoolean("qq-list")) return
        if (bot == null) return
        debug(e.groupID.toString() + e.groupName)
        if (e.groupID == config.getLong("qq")) {
            debug("进入在线玩家1" + e.message)
            if (e.message == config.getString("在线玩家")) {
                debug("进入在线玩家2")
                var playerlist = "在线玩家:"
                getOnlinePlayers().map {
                    playerlist = playerlist + it.displayName + "|"
                    debug(it.displayName)
                }
                bot!!.getGroup(config.getLong("qq")).sendMessage(playerlist)
            }
        }
    }

    @SubscribeEvent
    fun money(e: MiraiGroupMessageEvent) {
        if (!config.getBoolean("qq-money")) return
        val message = e.message
        if (message.contains(config.getString("查询") ?: "#查询")) {
            val player = message.removePrefix(config.getString("查询") ?: "#查询")
            debug(player)
            val sender = Bukkit.getPlayer(player)
            if (sender != null) {
                MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage(config.getString("查询消息")?.let { PlaceholderAPI.setPlaceholders(sender, it) })
            } else {
                MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("未能查询到在线玩家，格式${config.getString("查询") ?: "#查询"}player")
            }
        }
    }

    @SubscribeEvent
    fun start(e: MiraiBotOnlineEvent) {
        if (config.getBoolean("启动提醒")) {
            e.bot.getGroup(config.getLong("qq")).sendMessage(config.getString("启动消息"))
        }
    }

    @SubscribeEvent
    fun stop(e: PluginDisableEvent) {
        if (e.plugin.name == "sword" && config.getBoolean("关闭提醒")) {
            bot?.getGroup(config.getLong("qq"))?.sendMessage(config.getString("关闭消息"))
        }
    }

    @SubscribeEvent
    fun help(e: MiraiGroupMessageEvent) {
        if (!config.getBoolean("qq-help")) return
        if (e.message == "#帮助" || e.message == "#help") {
            MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage(config.getString("帮助消息"))
        }
        if (e.message == "#wiki" || e.message == "#百科") MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("wiki.mcwar.cn")
        if (e.message == "#map" || e.message == "#地图") MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("map.mcwar.cn")
        if (e.message == "#官网" || e.message == "#web") MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("www.mcwar.cn")
    }
}