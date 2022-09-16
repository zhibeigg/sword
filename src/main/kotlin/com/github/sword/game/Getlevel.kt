package com.github.sword.game

import com.bh.planners.api.PlannersAPI
import com.bh.planners.api.PlannersAPI.plannersProfile
import com.bh.planners.api.PlannersAPI.plannersProfileIsLoaded
import com.bh.planners.api.event.PlayerGetExperienceEvent
import com.bh.planners.api.event.PlayerLevelChangeEvent
import com.github.sword.sword
import org.bukkit.Bukkit
import org.bukkit.event.entity.PlayerDeathEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common5.Coerce
import taboolib.platform.util.sendLang

object Getlevel {
    @SubscribeEvent
    fun e(e: PlayerDeathEvent) {
        val player = e.entity.player
        val b = player!!.world.name
        val name = player.displayName
        val config = sword.config
        sword.say("§c玩家" + name + "死在了" + b)
        for (i in config.getStringList("world").indices) {
            if (b == config.getStringList("world")[i]) {
                player.sendMessage(config.getString("message"))
                if (player.plannersProfileIsLoaded && player.plannersProfile.job != null) {
                    val reduce = player.plannersProfile.experience * config.getInt("炁散百分之几") / -100
                    val event = PlayerGetExperienceEvent(player, reduce)
                    event.call()
                    if (!event.isCancelled) {
                        val mark = player.plannersProfile.job!!.level
                        player.plannersProfile.job!!.addExperience(reduce)
                        if (mark != player.plannersProfile.job!!.level) {
                            PlayerLevelChangeEvent(player, mark, player.plannersProfile.job!!.level).call()
                        }
                    }
                    player.sendLang("player-get-level", player.plannersProfile.level)
                }
            }
        }
    }
}