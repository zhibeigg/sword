package com.github.sword.game

import com.github.sword.sword
import org.bukkit.Material
import org.bukkit.event.block.BlockFromToEvent
import taboolib.common.platform.event.SubscribeEvent

object Stopegg {
    @SubscribeEvent
    fun e(e: BlockFromToEvent) {
        if (e.block.type == Material.DRAGON_EGG) e.isCancelled = sword.config.getBoolean("dragon-egg")
    }
}