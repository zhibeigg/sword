package com.github.sword.game

import com.github.sword.sword
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDamageByEntityEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.hasName

object Getsword {
    @SubscribeEvent
    fun e(attack: EntityDamageByEntityEvent) {
        if (attack.damager.type == EntityType.PLAYER) {
            val player = attack.damager.name
            val inv = Bukkit.getPlayer(player)!!.inventory
            val level = Bukkit.getPlayer(player)!!.level
            if (inv.itemInMainHand.hasName()) {
                val yl = inv.itemInMainHand.itemMeta!!.displayName
                if (yl == sword.config.getString("成长型武器名")) {
                    val damage = attack.finalDamage
                    attack.damage = level + damage
                    sword.say("§6纸杯龙剑：§a" + player + "§b使用成长型武器对§c" + attack.entity.name + "§b造成了§4" + attack.finalDamage + "§4伤害")
                    attack.damager.sendMessage(sword.config.getString("龙剑伤害信息"+attack.finalDamage))
                }
            }
        }
    }
}