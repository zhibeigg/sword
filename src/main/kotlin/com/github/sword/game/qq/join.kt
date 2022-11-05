package com.github.sword.game.qq

import com.germ.germplugin.api.util.VaultUtil
import com.github.sword.sword.config
import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.bukkit.event.bot.MiraiBotOnlineEvent
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiFriendMessageEvent
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiGroupMessageEvent
import me.dreamvoid.miraimc.bukkit.event.message.presend.MiraiGroupMessagePreSendEvent
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getOnlinePlayers
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent

object join {

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
        if (e.groupID == config.getLong("qq")) {
            if (e.messageToString == config.getString("在线玩家")) {
                val playerlist : List<String> = listOf("-----在线玩家-----")
                for (i in getOnlinePlayers().indices) {
                    playerlist.plus(getOnlinePlayers().toString()[i])
                }
                bot!!.getGroup(config.getLong("qq")).sendMessage(playerlist.toString())
            }
        }
    }

    @SubscribeEvent
    fun money(e: MiraiGroupMessageEvent) {
        if (!config.getBoolean("qq-money")) return
        val message = e.message
        if (message.contains(config.getString("查询") ?: "#查询")) {
            val sender = Bukkit.getPlayer(message.replace(config.getString("查询") ?: "#查询", null.toString()))
            val money = VaultUtil.getMoney(sender)
            if (money == 0.0) {
                MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("未能查询到玩家，格式${config.getString("查询") ?: "#查询"}player")
            } else {
                MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("炁源币：${money}")
            }
        }
    }

}