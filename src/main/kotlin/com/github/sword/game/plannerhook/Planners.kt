package com.github.sword.game.plannerhook

import com.bh.planners.api.event.PlayerCastSkillEvent
import com.bh.planners.api.event.PlayerSelectedJobEvent
import com.bh.planners.api.event.PlayerSkillUpgradeEvent
import io.github.battlepass.BattlePlugin
import io.github.battlepass.objects.quests.variable.QuestResult
import io.github.battlepass.quests.quests.external.executor.ExternalQuestExecutor
import org.bukkit.entity.Player
import taboolib.common.platform.event.SubscribeEvent


class Planners(var1: BattlePlugin?) : ExternalQuestExecutor(var1, "planners") {

    @SubscribeEvent(ignoreCancelled = true)
    fun onSkillUpgrade(var1: PlayerSkillUpgradeEvent) {
        val var2: Player = var1.player
        val var3: String = var1.skill.name
        this.execute("upgrade", var2) { var1x: QuestResult -> var1x.root(var3) }
    }

    @SubscribeEvent(ignoreCancelled = true)
    fun onSkillCast(var1: PlayerCastSkillEvent.Post) {
        val var2: Player = var1.player
        val var3 = var1.skill.option.name
        this.execute("skill_cast", var2) { var1x: QuestResult -> var1x.root(var3) }
    }

    @SubscribeEvent(ignoreCancelled = true)
    fun onClassChange(var1: PlayerSelectedJobEvent) {
        val var2: Player = var1.profile.player
        val var3: String = var1.profile.job?.name ?: "命炁者"
        this.execute("change_job", var2) { var1x: QuestResult -> var1x.root(var3) }
    }
}
