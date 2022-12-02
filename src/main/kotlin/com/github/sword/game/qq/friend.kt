package com.github.sword.game.qq

import com.Zrips.CMI.CMI
import com.github.sword.game.qq.group.papi
import com.github.sword.sword.config
import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiFriendMessageEvent
import taboolib.common.platform.event.SubscribeEvent


object friend {
    @SubscribeEvent
    fun onFriendMessageReceive(e: MiraiFriendMessageEvent) {
        if (!config.getBoolean("qq-money")) return
        val message = e.message
        if (message.contains(config.getString("查询") ?: "#查询 ")) {
            val player = message.removePrefix(config.getString("查询") ?: "#查询 ")
            val sender = CMI.getInstance().playerManager.getUser(player)
            if (sender == null) {
                MiraiBot.getBot(e.botID).getFriend(e.senderID).sendMessage("未能查询到玩家，格式\n${config.getString("查询") ?: "#查询 "}player")
            } else {
                MiraiBot.getBot(e.botID).getFriend(e.senderID).sendMessage(papi(config.getString("查询消息"), sender)?.replace(Regex("§[0-9a-zA-Z]"), ""))
            }
        }
    }
}
