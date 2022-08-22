package com.github.sword.game

import ac.github.oa.OriginAttribute
import ac.github.oa.api.OriginAttributeAPI
import ac.github.oa.api.event.entity.EntityDamageEvent
import com.github.sword.sword
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDamageByEntityEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.hasName

object Getsword {
    @SubscribeEvent
    fun e(attack: EntityDamageEvent) {
        if (attack.damageMemory.attacker.type == EntityType.PLAYER) {
            val player = attack.damageMemory.attacker.name
            val inv = Bukkit.getPlayer(player)!!.inventory
            val level = Bukkit.getPlayer(player)!!.level
            if (inv.itemInMainHand.hasName()) {
                val yl = inv.itemInMainHand.itemMeta!!.displayName
                if (yl == sword.config.getString("成长型武器名")) {
                    attack.damageMemory.damage.plus(level)
                    sword.say("§6纸杯龙剑：§a" + player + "§b使用成长型武器对§c" + attack.damageMemory.event.entity.name + "§b造成了§4" + attack.damageMemory.event.damage + "§4伤害")
                    attack.damageMemory.attacker.sendMessage(sword.config.getString("龙剑伤害信息"+attack.damageMemory.event.damage))
                }
            }
        }
    }
}