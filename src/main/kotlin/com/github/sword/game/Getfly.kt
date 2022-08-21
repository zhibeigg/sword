package com.github.sword.game

import com.germ.germplugin.api.GermPacketAPI
import com.germ.germplugin.api.SoundType.getSoundTypeFromType
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
        for (i in sword.config.getStringList("music-world").indices) {
            if (e.from.name == sword.config.getStringList("music-world").get(i)) {
                GermPacketAPI.playSound(player,
                    sword.config.getString("music")?.get(i).toString(),
                    getSoundTypeFromType("ambient"),
                    0F,
                    0F,
                    0F,
                    0,
                    1F,
                    1F,
                    true)
            }
        }
    }
}