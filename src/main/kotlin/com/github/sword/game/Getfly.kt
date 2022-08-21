package com.github.sword.game

import com.germ.germplugin.api.GermPacketAPI
import com.germ.germplugin.api.SoundType.getSoundTypeFromType
import com.github.sword.sword
import com.github.sword.sword.config
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
    @SubscribeEvent
    fun m(e: PlayerChangedWorldEvent) {
        for (i in config.getStringList("music-world").indices)
            if (e.from.name == config.getStringList("music-world").get(i)) {
                GermPacketAPI.playSound(/* player = */
                    e.player,
                    /* soundName = */
                    config.getString("music")?.get(i).toString(),
                    /* soundType = */
                    getSoundTypeFromType("ambient"),
                    /* x = */
                    0F,
                    /* volume = */
                    0F,
                    /* z = */
                    0F,
                    /* delayTick = */
                    0,
                    /* p7 = */
                    1F,
                    /* pitch = */
                    1F,
                    /* cycle = */
                    true)
            }
    }
}