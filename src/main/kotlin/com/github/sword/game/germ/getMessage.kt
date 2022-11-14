package com.github.sword.game.germ

import com.germ.germplugin.api.GermPacketAPI
import com.germ.germplugin.api.HudMessageType
import org.bukkit.event.server.BroadcastMessageEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.onlinePlayers

object getMessage {
    @SubscribeEvent
    fun autoMessage(e: BroadcastMessageEvent) {
        onlinePlayers.forEach {
            GermPacketAPI.sendHudMessage(it, HudMessageType.CENTER1, e.message)
        }
        e.isCancelled = true
    }
}