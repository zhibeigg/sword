package com.github.sword.game

import com.germ.germplugin.api.GermPacketAPI
import com.germ.germplugin.api.SoundType
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
        for (i in config.getStringList("title").indices) {
            if (e.player.world.name == config.getStringList("title")[i]) {
                e.player.sendTitle(
                    config.getStringList(e.player.world.name)[0],
                    config.getString("subtitle"),
                    config.getInt("in"),
                    config.getInt("on"),
                    config.getInt("out")
                )
        }
        }
        for (i in config.getStringList("music").indices) {
            if (e.player.world.name == config.getStringList("music")[i])
                GermPacketAPI.playSound(
                    e.player,
                    config.getStringList(e.player.world.name)[1],
                    SoundType.AMBIENT,
                    0f,
                    0f,
                    0f,
                    0,
                    1f,
                    1f,
                    true
                )
        }
    }
}