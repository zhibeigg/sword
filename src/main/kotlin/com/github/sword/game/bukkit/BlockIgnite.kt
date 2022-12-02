package com.github.sword.game.bukkit

import com.github.sword.sword.config
import org.bukkit.event.block.BlockIgniteEvent
import taboolib.common.platform.event.SubscribeEvent

object BlockIgnite {
    @SubscribeEvent
    fun e(e: BlockIgniteEvent) {
        if (!config.getStringList("非禁止火焰世界").contains(e.block.world.name)) {
            e.isCancelled = true
        }
    }
}