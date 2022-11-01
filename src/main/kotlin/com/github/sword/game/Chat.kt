package com.github.sword.game

import com.germ.germplugin.api.dynamic.gui.GermGuiButton
import com.germ.germplugin.api.event.gui.GermGuiButtonEvent
import taboolib.platform.compat.PlaceholderExpansion
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.serverct.ersha.dungeon.DungeonPlus
import taboolib.common.platform.event.SubscribeEvent

object Chat : PlaceholderExpansion {
    var ssss = 0

    @SubscribeEvent
    fun e(e: AsyncPlayerChatEvent) {
        val player = e.player
        if (ssss == 1) {
            DungeonPlus.teamManager.getTeam(player)?.sendTeamMessage("§f[§6队内消息§f] §7${player.displayName}§f§l:" + e.message)
            if (DungeonPlus.teamManager.getTeam(player) != null) {
                e.isCancelled = true
            }
        }
    }

    @SubscribeEvent
    fun check(e: GermGuiButtonEvent) {
        if (e.eventType == GermGuiButton.EventType.LEFT_CLICK && e.germGuiScreen.guiName == "game_gui_chat" && e.germGuiButton.indexName == "频道") {
            ssss = if (ssss == 0) {
                e.germGuiButton.setText("组队")
                1
            } else {
                e.germGuiButton.setText("世界")
                0
            }
        }
    }

    override val identifier: String
        get() = "sword"

    override fun onPlaceholderRequest(player: Player?, args: String): String {
        return if (ssss == 0 && args == "channel") {
            "世界"
        } else {
            "组队"
        }
    }

}