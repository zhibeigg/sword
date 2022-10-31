package com.github.sword.game

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.serverct.ersha.dungeon.DungeonPlus
import taboolib.common.platform.event.SubscribeEvent

object Chat {
    @SubscribeEvent
    fun e(e: AsyncPlayerChatEvent) {
        val player = e.player
        if (!DungeonPlus.dungeonManager.isDungeonWorld(player.world)) {
            if (PlaceholderAPI.setPlaceholders(player, "%ssss%") == "1") {
                DungeonPlus.teamManager.getTeam(player)?.sendTeamMessage(e.message)
                if (DungeonPlus.teamManager.getTeam(player) != null) {
                    e.isCancelled = true
                }
                return
            }
        }
        if (PlaceholderAPI.setPlaceholders(player, "%ssss%") == "1") {
            e.message = "*" + e.message
        }
    }
}