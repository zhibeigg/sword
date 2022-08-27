package com.github.sword.game

import com.github.sword.sword.config
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.hanging.HangingBreakByEntityEvent
import org.bukkit.event.player.PlayerArmorStandManipulateEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.EquipmentSlot
import taboolib.common.platform.event.SubscribeEvent

object Getinteract {

    @SubscribeEvent
    fun onInteract(event: PlayerInteractEntityEvent) {
        val player = event.player
        val entity = event.rightClicked
        val worldName = player.world.name
        val var5: Iterator<*> = config.getStringList("no-in-world").iterator()
        while (var5.hasNext()) {
            val wn = var5.next() as String
            if (worldName == wn && entity.name == "entity.ItemFrame.name") {
                if (player.isOp) {
                    return
                }
                if (event.hand == EquipmentSlot.HAND) {
                    event.isCancelled = true
                    player.sendMessage(config.getString("SendMessage"))
                    return
                }
                if (event.hand == EquipmentSlot.OFF_HAND) {
                    event.isCancelled = true
                    player.sendMessage(config.getString("SendMessage"))
                    return
                }
            }
        }
    }

    @SubscribeEvent
    fun onDamage(event: EntityDamageByEntityEvent) {
        if (event.damager.type == EntityType.PLAYER) {
            val player = event.damager as Player
            val entity = event.entity
            val worldName = player.world.name
            val var5: Iterator<*> = config.getStringList("no-in-world").iterator()
            while (var5.hasNext()) {
                val wn = var5.next() as String
                if (worldName == wn) {
                    if (player.isOp) {
                        return
                    }
                    if (entity.type == EntityType.ITEM_FRAME) {
                        event.isCancelled = true
                        player.sendMessage(config.getString("SendMessage"))
                        return
                    }
                    if (entity.type == EntityType.ARMOR_STAND) {
                        event.isCancelled = true
                        player.sendMessage(config.getString("SendMessage"))
                        return
                    }
                }
            }
        }
    }

    @SubscribeEvent
    fun onBreak(event: HangingBreakByEntityEvent) {
        if (event.remover!!.type == EntityType.PLAYER) {
            val player = event.remover as Player?
            val worldName = player!!.world.name
            val entity: Entity = event.entity
            val var5: Iterator<*> = config.getStringList("no-in-world").iterator()
            while (var5.hasNext()) {
                val wn = var5.next() as String
                if (worldName == wn) {
                    if (player.isOp) {
                        return
                    }
                    if (entity.name == "entity.ItemFrame.name") {
                        event.isCancelled = true
                        player.sendMessage(config.getString("SendMessage"))
                    }
                }
            }
        }
    }

    @SubscribeEvent
    fun onInteractPro(event: PlayerArmorStandManipulateEvent) {
        val player = event.player
        val worldName = player.world.name
        val var4: Iterator<*> = config.getStringList("no-in-world").iterator()
        while (var4.hasNext()) {
            val wn = var4.next() as String
            if (worldName == wn) {
                if (player.isOp) {
                    return
                }
                event.isCancelled = true
                player.sendMessage(config.getString("SendMessage"))
            }
        }
    }
}