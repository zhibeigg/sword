package com.github.sword.game

import com.germ.germplugin.api.dynamic.gui.GermGuiColor
import com.germ.germplugin.api.dynamic.gui.GermGuiLabel
import com.germ.germplugin.api.dynamic.gui.GermGuiScreen
import com.github.sword.sword.say
import ink.ptms.chemdah.api.event.collect.ObjectiveEvents
import ink.ptms.chemdah.api.event.collect.PlayerEvents
import ink.ptms.chemdah.core.quest.addon.AddonTrack.Companion.track
import org.bukkit.entity.Player
import taboolib.common.platform.event.SubscribeEvent


object Quest {
    var add = true

    fun updateadd(player: Player, description: List<String>?) {
        val germGuiScreen = GermGuiScreen.getGermGuiScreen("quest${player.name}")
        val germGuiLabel = germGuiScreen.getGuiPart("text") as GermGuiLabel
        val germGuiColor = germGuiScreen.getGuiPart("background") as GermGuiColor
        if (getcontain(description, germGuiLabel)) {
            if (description != null) {
                for (i in description.indices) {
                    germGuiLabel.texts.add(description[i])
                    say("条目添加成功")
                }
            }
        }
        germGuiColor.height = (9 * germGuiLabel.texts.indices.last + 20).toString()
    }

    @SubscribeEvent
    fun j(e: PlayerEvents.Selected) {
        val player = e.player
        val germGuiLabel = GermGuiLabel("text")
        val germGuiScreen = GermGuiScreen.getGermGuiScreen("quest${e.player.name}")
        val germGuiColor = GermGuiColor("background")
        val germGuiLabel2 = GermGuiLabel("title")
        germGuiColor.color = 0x7A000000
        germGuiColor.locationX = "100"
        germGuiColor.locationY = "40"
        germGuiColor.locationZ = "0"
        germGuiColor.width = "120"
        germGuiLabel2.locationX = "162"
        germGuiLabel2.locationY = "41"
        germGuiLabel2.locationZ = "1"
        germGuiLabel.locationX = "102"
        germGuiLabel.locationY = "52"
        germGuiLabel.locationZ = "1"
        germGuiColor.height = "9"
        germGuiLabel2.align = "center"
        germGuiLabel2.texts[0] = ("&6&l任务面板")
        if (add) {
            germGuiScreen.addGuiPart(germGuiLabel)
            germGuiScreen.addGuiPart(germGuiLabel2)
            germGuiScreen.addGuiPart(germGuiColor)
            add = false
        }
        say("为玩家${player.name}创建任务视图${germGuiLabel.texts}")
        germGuiScreen.openHud(player)
    }

    @SubscribeEvent
    fun m(e: ObjectiveEvents.Continue.Pre) {
        val description = e.task.track()?.description
        val player = e.playerProfile.player
        updateadd(player, description)
    }

    @SubscribeEvent
    fun m(e: ObjectiveEvents.Complete.Post) {
        val germGuiScreen = GermGuiScreen.getGermGuiScreen("quest${e.playerProfile.player.name}")
        val germGuiLabel = germGuiScreen.getGuiPart("text") as GermGuiLabel
        val description = e.task.track()?.description
        while (delete_contain(description, germGuiLabel))
        say("该条目删除成功")
    }

    private fun getcontain(description: List<String>?, germGuiLabel: GermGuiLabel): Boolean {
        if (description != null && germGuiLabel.texts != null) {
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

    private fun delete_contain(description: List<String>?, germGuiLabel: GermGuiLabel): Boolean {
        for (i in germGuiLabel.texts.indices) {
            if (description != null) {
                if (description.contains(germGuiLabel.texts[i])) {
                    germGuiLabel.texts.removeAt(i)
                    return true
                }
            }
        }
        return false
    }
}