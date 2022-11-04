package com.github.sword.game.qq

import com.germ.germplugin.api.util.VaultUtil
import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiFriendMessageEvent
import org.bukkit.Bukkit
import taboolib.common.platform.event.SubscribeEvent



object friend {
    @SubscribeEvent
    fun onFriendMessageReceive(e: MiraiFriendMessageEvent) {
        val message = e.message
        if (message.contains("查询")) {
            val sender = Bukkit.getPlayer(message.replace("查询", null.toString()))
            val money = VaultUtil.getMoney(sender)
            MiraiBot.getBot(e.botID).getFriend(e.senderID).sendMessage("炁源币：${money}")
        }
    }
}
