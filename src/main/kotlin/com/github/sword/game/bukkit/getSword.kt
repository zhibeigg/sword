package com.github.sword.game.bukkit

import ac.github.oa.api.OriginAttributeAPI
import ac.github.oa.api.event.entity.EntityLoadEquipmentEvent
import ac.github.oa.internal.core.attribute.AttributeData
import com.github.sword.sword
import com.github.sword.sword.debug
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.nms.getName
import taboolib.platform.util.hasName
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

object getSword {
    @SubscribeEvent
    fun e(attack: EntityLoadEquipmentEvent.Render) {
        if (attack.entity.type == EntityType.PLAYER) {
            val player: Player = attack.entity as Player
            val uuid = player.uniqueId
            val inv = player.inventory.itemInMainHand
            if (inv.hasName()) {
                val name = inv.getName()
                if (name == sword.config.getString("成长型武器名")) {
                    player.sendMessage(sword.config.getString("龙剑伤害信息") + player.level * sword.config.getInt("xlevel"))
                    val data = AttributeData()
                    data.merge(OriginAttributeAPI.loadList(listOf("体术攻击 +" + player.level * sword.config.getInt("xlevel"))))
                    OriginAttributeAPI.setExtra(uuid, "sword", data)
                    OriginAttributeAPI.callUpdate(player)
                    debug("已更新call{${player}|${name}}")
                } else {
                    OriginAttributeAPI.removeExtra(uuid, "sword")
                    OriginAttributeAPI.callUpdate(player)
                    debug("1已删除 sword 属性")
                }
            } else {
                OriginAttributeAPI.removeExtra(uuid, "sword")
                OriginAttributeAPI.callUpdate(player)
                debug("2已删除 sword 属性")
            }
        }
    }
}