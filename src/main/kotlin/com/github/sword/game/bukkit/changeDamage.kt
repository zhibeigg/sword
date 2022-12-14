package com.github.sword.game.bukkit

import com.github.sword.sword
import com.github.sword.sword.config
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByBlockEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import taboolib.common.platform.event.SubscribeEvent

object changeDamage {
    @SubscribeEvent
    fun e(e: EntityDamageByBlockEvent) {
        val type = e.cause
        val config = sword.config
        if (type == DamageCause.HOT_FLOOR && config.getInt("magma") == 0) e.isCancelled =
            true
        if (type == DamageCause.HOT_FLOOR && config.getInt("magma") != 0) e.damage =
            config.getInt("magma").toDouble()
        if (type == DamageCause.CONTACT && config.getInt("touch") == 0) e.isCancelled =
            true
        if (type == DamageCause.CONTACT && config.getInt("touch") != 0) e.damage =
            config.getInt("touch").toDouble()
    }

    @SubscribeEvent
    fun fire(e: EntityDamageEvent) {
        if (!e.isCancelled ) {
            if (e.cause == DamageCause.FIRE || e.cause == DamageCause.FIRE_TICK) {
                e.damage = config.getInt("fire-damage").toDouble()
            }
        }
    }

    @SubscribeEvent
    fun drop(e: EntityDamageEvent) {
        if (e.entityType == EntityType.PLAYER) {
            val player = e.entity as Player
            if (e.cause == DamageCause.FALL && config.getStringList("no-fall").contains(player.world.name)) {
                e.isCancelled = true
            }
        }
        return
    }
}