package com.github.sword.game

import ac.github.oa.api.ItemAPI.takeDurability
import ac.github.oa.api.event.entity.OriginCustomDamageEvent
import ac.github.oa.internal.core.attribute.AttributeManager
import ac.github.oa.internal.core.attribute.equip.Hand
import ac.github.oa.internal.core.attribute.equip.OffHand
import com.github.sword.sword.config
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.serverct.ersha.dungeon.common.team.Team
import org.serverct.ersha.dungeon.internal.manager.TeamManager
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent

object Getteam {
    @SubscribeEvent(ignoreCancelled = true)
    fun e(e: OriginCustomDamageEvent) {
        if (e.isCancelled) return
        val team1 = (e.attacker as? Player)?.let { TeamManager().getTeam(it) }
        val team2 = (e.entity as? Player)?.let { TeamManager().getTeam(it) }
        // 取消攻击事件
        if (team1 != null && team2 != null) {
            if (team1 == team2 && config.getStringList("team-world").contains(e.entity.world.name)) {
                e.isCancelled = true
            }
        }
    }
}