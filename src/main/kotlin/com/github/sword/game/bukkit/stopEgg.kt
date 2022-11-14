package com.github.sword.game.bukkit

import com.github.sword.sword
import org.bukkit.Material.DRAGON_EGG
import org.bukkit.Sound
import org.bukkit.event.block.BlockFromToEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType.BLINDNESS
import taboolib.common.platform.event.SubscribeEvent

object stopEgg {
    @SubscribeEvent
    fun e(e: BlockFromToEvent) {
        if (e.block.type == DRAGON_EGG) e.isCancelled = sword.config.getBoolean("dragon-egg")
    }

    @SubscribeEvent
    fun m(e: PlayerInteractEvent) {
        val sound = sword.config.getString("egg-sound")
        val block = e.isBlockInHand
        val hasblock = e.hasBlock()
        if ( hasblock && !block ) {
            val type = e.clickedBlock?.type
            if (type == DRAGON_EGG) {
                val player = e.player
                player.sendMessage("§7[§4系统§7]§c管住自己的手")
                player.addPotionEffect(PotionEffect(BLINDNESS, 40, 40))
                if (sound != null) {
                    player.playSound(player.location, Sound.valueOf(sound.replace('.', '_').uppercase()), 1.0f, 1.0f)
                }
                player.location.world!!.strikeLightning(player.location)
            }
        }
    }
}

