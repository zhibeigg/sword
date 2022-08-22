package com.github.sword.game

import com.bh.planners.core.kether.compat.attribute.AttributeBridge
import com.github.sword.sword
import org.bukkit.event.player.PlayerChangedMainHandEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.nms.getName
import taboolib.platform.util.hasName

object Getsword {
    @SubscribeEvent
    fun e(attack: PlayerChangedMainHandEvent) {
        val player = attack.player
        val inv = player.inventory.itemInMainHand
        if (inv.hasName()){
            val name = inv.getName()
            if (name == sword.config.getString("成长型武器名")) {
                player.sendMessage(sword.config.getString("龙剑伤害信息") + player.level)
                AttributeBridge.INSTANCE?.addAttributes(
                    "sword",
                    player.uniqueId,
                    -1,
                    listOf("体术攻击 +" + player.level),
                )
            } else {
                AttributeBridge.INSTANCE?.removeAttributes(
                    player.uniqueId,
                    "sword"
                )
            }
        } else {
            AttributeBridge.INSTANCE?.removeAttributes(
                player.uniqueId,
                "sword"
            )
        }
        player.sendMessage("切换主手")
    }
}