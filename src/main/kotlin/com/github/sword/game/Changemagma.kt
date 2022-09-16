package com.github.sword.game

import com.github.sword.sword
import org.bukkit.event.entity.EntityDamageByBlockEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import taboolib.common.platform.event.SubscribeEvent

object Changemagma {
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
}