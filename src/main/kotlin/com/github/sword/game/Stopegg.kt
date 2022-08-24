package com.github.sword.game

import com.github.sword.sword
import org.bukkit.Material.DRAGON_EGG
import org.bukkit.Sound
import org.bukkit.event.block.BlockFromToEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType.BLINDNESS
import taboolib.common.platform.event.SubscribeEvent

object Stopegg {
    @SubscribeEvent
    fun e(e: BlockFromToEvent) {
        if (e.block.type == DRAGON_EGG) e.isCancelled = sword.config.getBoolean("dragon-egg")
    }

    @SubscribeEvent
    fun m(e: PlayerInteractEvent) {
        if (e.clickedBlock!!.blockData.material == DRAGON_EGG) {
            val player = e.player
            player.sendMessage("§7[§4系统§7]§c管住自己的手")
            player.playSound(player, Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1F, 1F)
            player.addPotionEffect(PotionEffect(BLINDNESS, 20, 5))
            player.location.world!!.strikeLightning(player.location)
        }
    }
}

