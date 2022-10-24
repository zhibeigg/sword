package com.github.sword.game

import ac.github.oa.api.event.entity.OriginCustomDamageEvent
import com.github.sword.sword.config
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import taboolib.common.platform.event.SubscribeEvent

object NoDamage {
    @SubscribeEvent
    fun e(e: OriginCustomDamageEvent) {
        if (e.damager.type == EntityType.PLAYER && config.getBoolean("是否开启禁止攻击")) {
            val player = e.damager as Player
            val lore = player.inventory.itemInMainHand.itemMeta?.lore
            if (lore != null) {
                var allow = true
                for (i in lore.indices) {
                    if (config.getString("攻击武器lore")?.let { lore[i].contains(it) } == true) {
                        allow = false
                    }
                }
                e.isCancelled = allow
            }
        }
    }
}