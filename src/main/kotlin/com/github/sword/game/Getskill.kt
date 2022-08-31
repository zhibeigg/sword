package com.github.sword.game

import com.bh.planners.api.event.PlayerCastSkillEvent
import com.germ.germplugin.api.GermPacketAPI
import com.germ.germplugin.api.HudMessageType
import com.germ.germplugin.api.KeyType
import com.germ.germplugin.api.event.GermKeyDownEvent
import com.github.sword.sword.config
import com.github.sword.sword.parse
import taboolib.common.platform.event.SubscribeEvent

object Getskill {
    var sk = false

    @SubscribeEvent
    fun m(e: PlayerCastSkillEvent.Pre) {
        val player = e.player
        val world = player.world.name
        val sound = config.getString("noskill-sound")
        if (sk) {
            for (i in config.getStringList("skill-black").indices) {
                if (world == config.getStringList("skill-black")[i]) {
                    e.isCancelled = true
                    player.sendTitle(parse("&4禁止释放"), parse("&b当前世界禁止释放技能"), 10, 10, 10)
                    if (sound != null) {
                        player.playSound(
                            player.location,
                            org.bukkit.Sound.valueOf(sound.replace('.', '_').uppercase()),
                            1.0f,
                            1.0f
                        )
                    }
                }
            }
        }
    }

    @SubscribeEvent
    fun e(e: PlayerCastSkillEvent.Pre) {
        if (!sk) {
            e.isCancelled = true
        }
    }

    @SubscribeEvent
    fun m(e: GermKeyDownEvent) {
        val key = e.keyType
        val player = e.player
        val slot = player.inventory.heldItemSlot
        if (sk) {
            if (key == KeyType.KEY_1 || key == KeyType.KEY_2 || key == KeyType.KEY_3 || key == KeyType.KEY_4 || key == KeyType.KEY_5) {
                player.inventory.heldItemSlot = slot
            }
        }
    }

    @SubscribeEvent
    fun e(e: GermKeyDownEvent) {
        val key = e.keyType
        val player = e.player
        if (key == KeyType.KEY_R && !sk) {
            player.sendTitle(
                config.getString("skill-mode-title1")?.let { parse(it) },
                config.getString("skill-mode-subtitle1")?.let { parse(it) },
                10,
                20,
                10
            )
            sk = true
        } else if (key == KeyType.KEY_R) {
            player.sendTitle(
                config.getString("skill-mode-title2")?.let { parse(it) },
                config.getString("skill-mode-subtitle2")?.let { parse(it) },
                10,
                20,
                10
            )
            sk = false
        }
    }
}