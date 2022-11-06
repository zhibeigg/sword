package com.github.sword.game.qq

import com.github.sword.sword
import me.clip.placeholderapi.PlaceholderAPI
import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiFriendMessageEvent
import org.bukkit.Bukkit
import taboolib.common.platform.event.SubscribeEvent


object friend {
    @SubscribeEvent
    fun onFriendMessageReceive(e: MiraiFriendMessageEvent) {
        if (!sword.config.getBoolean("qq-money")) return
        val message = e.message
        if (message.contains("查询")) {
            val sender = Bukkit.getPlayer(message.removePrefix("查询"))
            if (sender == null) {
                MiraiBot.getBot(e.botID).getFriend(e.senderID).sendMessage("未能查询到玩家，格式 查询player")
            } else {
                MiraiBot.getBot(e.botID).getFriend(e.senderID).sendMessage(sword.config.getString("查询消息")?.let { PlaceholderAPI.setPlaceholders(sender, it) })
            }
        }
    }
}
