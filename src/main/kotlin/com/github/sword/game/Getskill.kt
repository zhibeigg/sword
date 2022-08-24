package com.github.sword.game

import com.bh.planners.api.event.PlayerKeydownEvent
import com.germ.germplugin.api.KeyType
import com.germ.germplugin.api.event.GermKeyDownEvent
import com.github.sword.sword
import com.github.sword.sword.config
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import taboolib.common.platform.event.SubscribeEvent

object Getskill {
    var mode = config.getBoolean("default-skill-mode")

    @SubscribeEvent
    fun e(e: PlayerKeydownEvent) {
        val player = e.player
        val world = player.world.name
        for (i in config.getStringList("skill-black").indices) {
            if (world == config.getStringList("skill-black")[i]){
                e.isCancelled
            }
        }
    }

    @SubscribeEvent
    fun m(e: PlayerKeydownEvent) {
        val player = e.player
        if (!mode){
            e.isCancelled
        }
    }

    @SubscribeEvent
    fun i(e: InventoryClickEvent) {
        if (e.click == ClickType.NUMBER_KEY && mode) {
            e.isCancelled
        }
    }

    @SubscribeEvent
    fun k(e: GermKeyDownEvent) {
        val key = e.keyType
        val player = e.player
        if (key == config.getString("skill-mode-key")?.let { KeyType.valueOf(it) } && !mode ) {
            player.sendTitle(config.getString("skill-mode-title1")?.let { sword.parse(it) }, config.getString("skill-mode-subtitle1")?.let { sword.parse(it) }, 10, 20 ,10)
            mode = true
        } else if (key == config.getString("skill-mode-key")?.let { KeyType.valueOf(it) } && mode) {
            player.sendTitle(config.getString("skill-mode-title2")?.let { sword.parse(it) }, config.getString("skill-mode-subtitle2")?.let { sword.parse(it) }, 10, 20 ,10)
            mode = false
        }
    }
}