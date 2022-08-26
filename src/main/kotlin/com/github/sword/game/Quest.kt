package com.github.sword.game

import com.germ.germplugin.api.dynamic.gui.GermGuiColor
import com.germ.germplugin.api.dynamic.gui.GermGuiLabel
import com.germ.germplugin.api.dynamic.gui.GermGuiScreen
import com.github.sword.sword.say
import ink.ptms.chemdah.api.event.collect.ObjectiveEvents
import ink.ptms.chemdah.core.quest.addon.AddonTrack.Companion.track
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player
import taboolib.common.platform.event.SubscribeEvent


object Quest {
    var description: List<String>? = null
    val germGuiLabel = GermGuiLabel("text")
    val germGuiScreen = GermGuiScreen.getGermGuiScreen("sword")
    var create = true

    fun createsb(player: Player) {
        val germGuiColor = GermGuiColor("background")
        germGuiColor.color = 0x7A000000
        germGuiColor.locationX = "100"
        germGuiColor.locationY = "40"
        germGuiColor.locationZ = "0"
        germGuiColor.height = (9 * germGuiLabel.texts.indices.last + 9).toString()
        germGuiColor.width = "120"
        germGuiLabel.locationX = "102"
        germGuiLabel.locationY = "41"
        germGuiLabel.locationZ = "1"
        germGuiLabel.texts = PlaceholderAPI.setPlaceholders(player, germGuiLabel.texts)
        if (create) {
            germGuiScreen.guiParts[0] = germGuiLabel
            germGuiScreen.guiParts[1] = germGuiColor
            say("为玩家${player.name}创建任务视图${germGuiLabel.texts}")
            create = false
        }
        germGuiScreen.openHud(player)
    }
    @SubscribeEvent
    fun m(e: ObjectiveEvents.Continue.Pre) {
        description = e.task.track()?.description
        val player = e.playerProfile.player
        if (getcontain(description)) {
            if (description != null) {
                for (i in description!!.indices) {
                    germGuiLabel.texts.add(description!![i])
                }
            }
        }
        createsb(player)
        say("更新任务hud")
    }

    @SubscribeEvent
    fun q(e: ObjectiveEvents.Complete.Pre) {
        val complete_description = e.task.track()?.description
        if (!getcontain(complete_description)) {
            val int = getcontain_int(complete_description)
            if (int != null) {
                germGuiLabel.texts[int] = null
            }
        }
        say("${e.playerProfile.player}完成任务删除$complete_description")
    }

    fun getcontain(description: List<String>?): Boolean {
        if (description != null) {
            for (i in germGuiLabel.texts.indices) {
                for (m in description.indices) {
                    if (germGuiLabel.texts[i] == description[m]) {
                        return false
                    }
                }
            }
        }
        return true
    }

    fun getcontain_int(description: List<String>?): Int? {
        if (description != null) {
            for (i in germGuiLabel.texts.indices) {
                for (m in description.indices) {
                    if (germGuiLabel.texts[i] == description[m]) {
                        return i
                    }
                }
            }
        }
        return null
    }
}