package com.github.sword.game

import com.github.sword.sword
import org.bukkit.Material.DRAGON_EGG
import org.bukkit.event.block.BlockFromToEvent
import org.bukkit.event.player.PlayerInteractEvent
import taboolib.common.platform.event.SubscribeEvent

object Stopegg {
    @SubscribeEvent
    fun e(e: BlockFromToEvent) {
        if (e.block.type == DRAGON_EGG) e.isCancelled = sword.config.getBoolean("dragon-egg")
    }

    @SubscribeEvent
    fun m(e: PlayerInteractEvent) {
        if (e.clickedBlock?.blockData?.material == DRAGON_EGG) {
            e.player.sendMessage("§7[§4系统§7]§c管住自己的手")
        }
    }
}