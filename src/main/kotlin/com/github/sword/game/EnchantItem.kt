package com.github.sword.game

import com.github.sword.sword.config
import org.bukkit.Sound
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.enchantment.PrepareItemEnchantEvent
import taboolib.common.platform.event.SubscribeEvent

object EnchantItem {
    @SubscribeEvent
    fun done(e: EnchantItemEvent) {
        if (!config.getBoolean("禁用附魔")) return
        val sound = config.getString("禁用附魔音效")
        e.isCancelled = config.getBoolean("禁用附魔")
        val player = e.enchanter
        player.sendMessage(config.getString("附魔信息"))
        if (sound != null) {
            player.playSound(
                player.location,
                org.bukkit.Sound.valueOf(sound.replace('.', '_').uppercase()),
                1.0f,
                1.0f
            )
        }
    }

    @SubscribeEvent
    fun put(e: PrepareItemEnchantEvent) {
        if (!config.getBoolean("禁用放入附魔台")) return
        val sound = config.getString("禁用附魔音效")
        e.isCancelled = config.getBoolean("禁用放入附魔台")
        val player = e.enchanter
        player.sendMessage(config.getString("附魔信息"))
        if (sound != null) {
            player.playSound(
                player.location,
                org.bukkit.Sound.valueOf(sound.replace('.', '_').uppercase()),
                1.0f,
                1.0f
            )
        }
    }
}