package com.github.sword.game

import com.github.sword.sword.config
import org.bukkit.Sound
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.enchantment.PrepareItemEnchantEvent
import taboolib.common.platform.event.SubscribeEvent

object EnchantItem {
    @SubscribeEvent
    fun done(e: EnchantItemEvent) {
        e.isCancelled = config.getBoolean("禁用附魔")
        val player = e.enchanter
        player.sendMessage(config.getString("附魔信息"))
        player.playSound(player, Sound.ITEM_SHIELD_BREAK, 1F, 1F)
    }

    @SubscribeEvent
    fun put(e: PrepareItemEnchantEvent) {
        e.isCancelled = config.getBoolean("禁用放入附魔台")
        val player = e.enchanter
        player.sendMessage(config.getString("附魔信息"))
        player.playSound(player, Sound.ITEM_SHIELD_BREAK, 1F, 1F)
    }
}