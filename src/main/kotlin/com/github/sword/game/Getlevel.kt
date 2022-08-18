package com.github.sword.game

import com.github.sword.sword
import org.bukkit.event.entity.PlayerDeathEvent
import taboolib.common.platform.event.SubscribeEvent

object Getlevel {
    /**
     * 监听器仅接受一个参数，为事件的类型
     */
    @SubscribeEvent
    fun e(e: PlayerDeathEvent) {
        val player = e.entity.player
        val b = player!!.world.name
        val name = player.displayName
        val config = sword.config
        sword.say("§c玩家" + name + "死在了" + b)
        for (i in config.getStringList("world").indices) {
            if (b == config.getStringList("world").get(i)) {
                player.sendMessage(config.getString("message"))
            }
        }
    }
}