package com.github.sword.game

import ac.github.oa.api.ItemAPI.takeDurability
import ac.github.oa.api.event.entity.OriginCustomDamageEvent
import ac.github.oa.internal.core.attribute.AttributeManager
import ac.github.oa.internal.core.attribute.equip.Hand
import ac.github.oa.internal.core.attribute.equip.OffHand
import com.github.sword.sword.config
import com.github.sword.sword.debug
import com.github.sword.sword.say
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.serverct.ersha.dungeon.DungeonPlus
import org.serverct.ersha.dungeon.common.team.Team
import org.serverct.ersha.dungeon.internal.manager.TeamManager
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent

object Getteam {
    @SubscribeEvent(ignoreCancelled = true)
    fun e(e: OriginCustomDamageEvent) {
        val team1 = (e.attacker as? Player)?.let { DungeonPlus.teamManager.getTeam(it) }
        val team2 = (e.entity as? Player)?.let { DungeonPlus.teamManager.getTeam(it) }
        // 取消攻击事件
        if (team1 != null && team2 != null) {
            if (team1 == team2 && config.getStringList("team-world").contains(e.entity.world.name)) {
                e.isCancelled = true
                debug("事件已取消 攻击者：${e.attacker}${team1} 受击者：${e.entity}${team2}")
            }
            debug("事件未取消但通过第一检测 攻击者：${e.attacker}${team1} 受击者：${e.entity}${team2}")
        } else {
            debug("事件未取消 攻击者：${e.attacker}${team1} 受击者：${e.entity}${team2}")
        }
    }
}