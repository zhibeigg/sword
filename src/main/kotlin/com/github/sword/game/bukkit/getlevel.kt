package com.github.sword.game.bukkit

import com.bh.planners.api.PlannersAPI.plannersProfile
import com.bh.planners.api.PlannersAPI.plannersProfileIsLoaded
import com.bh.planners.api.event.PlayerGetExperienceEvent
import com.bh.planners.api.event.PlayerLevelChangeEvent
import com.bh.planners.core.pojo.player.PlayerJob
import com.github.sword.sword
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.sendLang
import kotlin.math.abs

object getlevel {
    @SubscribeEvent
    fun e(e: PlayerDeathEvent) {
        val player = e.entity.player
        val worldname = player!!.world.name
        val name = player.displayName
        val config = sword.config
        sword.say("§c玩家" + name + "死在了" + worldname)
        if (config.getStringList("world").contains(worldname)) {
            if (player.plannersProfileIsLoaded && player.plannersProfile.job != null) {
                val reduce = player.plannersProfile.maxExperience * config.getInt("炁散百分之几") / -100
                takeexp(player, player.plannersProfile.job!!, reduce)
            }
        }
    }

    fun takeexp(player: Player, job: PlayerJob, reduce: Int) {
        val beforelevel = job.level
        val event = PlayerGetExperienceEvent(player, reduce)
        event.call()
        if (!event.isCancelled) {
            if (job.experience < abs(reduce)) {
                val exp = job.maxExperience + reduce + job.experience
                job.counter.addLevel(-1)
                PlayerLevelChangeEvent(player, beforelevel, job.level).call()
                job.addExperience(exp)
            } else if (job.experience >= abs(reduce)) {
                job.addExperience(reduce)
            }
            player.sendLang("player-take-level", player.plannersProfile.level)
        }
    }

}