package com.github.sword.game

import com.github.sword.sword
import org.bukkit.event.player.PlayerChangedWorldEvent
import taboolib.common.platform.event.SubscribeEvent

object Getfly {
    @SubscribeEvent
    fun e(e: PlayerChangedWorldEvent) {
        val player = e.player
        if (player.allowFlight) {
            player.isFlying = false
            val name = player.displayName
            sword.say("§c玩家$name§c飞行已经取消")
        }
    }
}