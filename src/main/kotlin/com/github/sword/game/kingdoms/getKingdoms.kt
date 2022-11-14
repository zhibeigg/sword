package com.github.sword.game.kingdoms

import org.kingdoms.events.invasion.KingdomInvadeEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.onlinePlayers

object getKingdoms {
    @SubscribeEvent
    fun e(e: KingdomInvadeEvent) {
        val kingdom1 = e.invasion.attacker.name
        val kingdom2 = e.invasion.defender.name
        val player = e.invasion.invaderPlayer.name
        onlinePlayers.forEach {
            it.sendMessage("§c[§e战争通告§c] §b国家§c§l${kingdom1}§b的成员§4§l${player}§b对国家§a§l${kingdom2}§b发动战争！")
        }
    }
}