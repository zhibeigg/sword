package com.github.sword.game

import com.github.sword.sword.config
import org.bukkit.Bukkit
import taboolib.common.platform.Schedule

object Random {
    @Schedule(period = 12000, async = true)
    fun randomTask() {
        if (config.getBoolean("random", def = false)) {
            val player = Bukkit.getOnlinePlayers().random()
            val commandlist = config.getStringList("random-command")
            for (i in commandlist.indices) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandlist[i].replace("%player%", player.displayName))
            }
        }
    }
}