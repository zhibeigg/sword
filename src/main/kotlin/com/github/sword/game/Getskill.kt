package com.github.sword.game

import com.bh.planners.api.event.PlayerCastSkillEvent
import com.germ.germplugin.api.KeyType
import com.germ.germplugin.api.event.GermKeyDownEvent
import com.github.sword.game.Getskill.sk
import com.github.sword.sword.config
import com.github.sword.sword.parse
import com.github.sword.sword.say
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerChangedMainHandEvent
import taboolib.common.platform.event.SubscribeEvent

object Getskill {
    var sk = false

    @SubscribeEvent
    fun e(e: PlayerCastSkillEvent.Pre) {
        val player = e.player
        val world = player.world.name
        val sound = config.getString("noskill-sound")
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

    @SubscribeEvent
    fun m(e: PlayerCastSkillEvent.Pre) {
        if (!sk) {
            e.isCancelled = true
        }
    }

    @SubscribeEvent
    fun f(e: PlayerChangedMainHandEvent) {
        e.player.sendMessage(e.mainHand.name)
    }

    @SubscribeEvent
    fun k(e: GermKeyDownEvent) {
        val key = e.keyType
        val player = e.player
        player.sendMessage(key.name)
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