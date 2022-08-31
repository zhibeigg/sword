package com.github.sword.game

import com.germ.germplugin.api.GermPacketAPI
import com.germ.germplugin.api.SoundType
import com.github.sword.sword.config
import org.bukkit.event.player.PlayerLevelChangeEvent
import taboolib.common.platform.event.SubscribeEvent

object Getup {
    @SubscribeEvent
    fun Event2(event: PlayerLevelChangeEvent) {
        val player = event.player
        if (event.newLevel >= event.oldLevel) {
            event.player.sendTitle("§e§l小伙子,你升级了！", "§6§l新的等级为§f§l" + event.newLevel, 20, 50, 20)
            event.player.sendMessage(config.getString("分割线"))
            event.player.sendMessage(config.getString("前缀内容") + player.name + config.getString("升级内容") + event.oldLevel + config.getString("等级中间的内容") + event.newLevel)
            event.player.sendMessage(config.getString("分割线"))
            GermPacketAPI.playSound(
                player,
                config.getString("升级音乐"),
                SoundType.MASTER,
                0f,
                0f,
                0f,
                0,
                1f,
                1f,
                false
            )
        }
    }

    @SubscribeEvent
    fun Event1(event: PlayerLevelChangeEvent) {
        val player = event.player
        if (event.newLevel <= event.oldLevel) {
            event.player.sendTitle("§e§l小伙子,你降级了！", "§6§l新的等级为§f§l" + event.newLevel, 20, 50, 20)
            event.player.sendMessage(config.getString("分割线"))
            event.player.sendMessage(config.getString("前缀内容") + player.name + config.getString("降级内容") + event.oldLevel + config.getString("等级中间的内容") + event.newLevel)
            event.player.sendMessage(config.getString("分割线"))
            GermPacketAPI.playSound(
                player,
                config.getString("降级音乐"),
                SoundType.MASTER,
                0f,
                0f,
                0f,
                0,
                1f,
                1f,
                false
            )
        }
    }
}