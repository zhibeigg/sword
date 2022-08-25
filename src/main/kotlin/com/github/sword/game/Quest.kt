package com.github.sword.game

import com.germ.germplugin.api.dynamic.gui.*
import ink.ptms.chemdah.api.event.collect.QuestEvents
import ink.ptms.chemdah.core.quest.Template
import ink.ptms.chemdah.core.quest.addon.AddonUI.Companion.ui
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import taboolib.common.platform.event.SubscribeEvent
import java.io.File

object Quest {
    private val germGuiScreen: GermGuiScreen = GermGuiScreen.getGermGuiScreen("swordsb", YamlConfiguration.loadConfiguration(File("sword")))

    private fun createsb(player: Player, quest: Template) {
        val text = quest.ui()?.description
        if (text != null) {
            for (i in text.indices)
                player.sendMessage(text[i])
        }
        val label = germGuiScreen.getGuiPart("text") as GermGuiLabel
        if (text == label.texts) {
            germGuiScreen.openHud(player)
        } else if (label.texts[0]!!.contentEquals("暂无任务")) {
            label.texts = text
            germGuiScreen.openHud(player)
        } else {
            label.texts = text
        }
    }

    @SubscribeEvent
    fun m(e: QuestEvents.Accept.Pre) {
        if (e.quest.ui()?.description != null) {
            createsb(e.playerProfile.player, e.quest)
        }
    }
}