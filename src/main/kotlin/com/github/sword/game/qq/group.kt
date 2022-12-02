package com.github.sword.game.qq

import com.Zrips.CMI.CMI
import com.Zrips.CMI.Containers.CMIUser
import com.github.sword.sword.config
import com.github.sword.sword.debug
import me.clip.placeholderapi.PlaceholderAPI
import me.dreamvoid.miraimc.api.MiraiBot
import me.dreamvoid.miraimc.bukkit.event.bot.MiraiBotOnlineEvent
import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiGroupMessageEvent
import org.bukkit.Bukkit.getOnlinePlayers
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.server.PluginDisableEvent
import org.kingdoms.constants.group.Kingdom
import org.kingdoms.main.Kingdoms
import taboolib.common.platform.event.SubscribeEvent

object group {

    var bot : MiraiBot? = null

    @SubscribeEvent
    fun e(e: MiraiBotOnlineEvent) {
        bot = e.bot
    }


    @SubscribeEvent
    fun join(e: PlayerJoinEvent) {
        if (!config.getBoolean("qq-join")) return
        val name = e.player.displayName
        if (bot == null) return
        bot!!.getGroup(config.getLong("qq")).sendMessage(config.getString("join")?.replace("%player%", name))
    }

    @SubscribeEvent
    fun quit(e: PlayerQuitEvent) {
        if (!config.getBoolean("qq-quit")) return
        val name = e.player.displayName
        if (bot == null) return
        bot!!.getGroup(config.getLong("qq")).sendMessage(config.getString("quit")?.replace("%player%", name))
    }

    @SubscribeEvent
    fun getmessage(e: MiraiGroupMessageEvent) {
        if (!config.getBoolean("qq-list")) return
        if (bot == null) return
        debug(e.groupID.toString() + e.groupName)
        if (e.groupID == config.getLong("qq")) {
            debug("进入在线玩家1" + e.message)
            if (e.message == config.getString("在线玩家")) {
                debug("进入在线玩家2")
                if (getOnlinePlayers().isEmpty()) {
                    bot!!.getGroup(config.getLong("qq")).sendMessage("压根没人玩~~拉胯")
                    return
                }
                var playerlist = "┏在线玩家┓"
                getOnlinePlayers().map {
                    playerlist = playerlist + "\n" + it.displayName
                    debug(it.displayName)
                }
                bot!!.getGroup(config.getLong("qq")).sendMessage(playerlist)
            }
        }
    }

    @SubscribeEvent
    fun kingdoms(e: MiraiGroupMessageEvent) {
        if (!config.getBoolean("qq-kingdoms")) return
        val message = e.message
        if (message.contains(config.getString("国家查询") ?: "#王国 ")) {
            val name = message.removePrefix(config.getString("国家查询") ?: "#王国 ")
            debug(name)
            val var1 = Kingdom.getKingdom(name)
            debug(var1.toString())
            if (var1 != null) {
                MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage(kingdommessage(var1) ?: "未能查询到国家，格式\n${config.getString("国家查询") ?: "#王国 "}kingdom")
            } else {
                MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("未能查询到国家，格式\n${config.getString("国家查询") ?: "#王国 "}kingdom")
            }
        }
    }

    fun kingdommessage(kingdom: Kingdom) : String? {
        return config.getString("国家信息")
            ?.replace("%name%", kingdom.name)
            ?.replace("%king%", kingdom.king.player.displayName)
            ?.replace("%money%", kingdom.bank.toInt().toString())
            ?.replace("%points%", kingdom.resourcePoints.toString())
            ?.replace("%lands%", kingdom.lands.toString())
            ?.replace("%max_lands%", kingdom.maxLands.toString())
            ?.replace("%number%", kingdom.members.size.toString())
            ?.replace("%max_number%", kingdom.maxMembers.toString())
            ?.replace("x", kingdom.nexus.x.toString())
            ?.replace("y", kingdom.nexus.y.toString())
            ?.replace("z", kingdom.nexus.z.toString())
    }

    @SubscribeEvent
    fun find(e: MiraiGroupMessageEvent) {
        if (!config.getBoolean("qq-money")) return
        val message = e.message
        if (message.contains(config.getString("查询") ?: "#查询 ")) {
            val player = message.removePrefix(config.getString("查询") ?: "#查询 ")
            debug(player)
            val sender = CMI.getInstance().playerManager.getUser(player)
            if (sender != null) {
                MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage(papi(config.getString("查询消息"), sender)?.replace(Regex("§[0-9a-zA-Z]"), ""))
            } else {
                MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("未能查询到玩家，格式\n${config.getString("查询") ?: "#查询 "}player")
            }
        }
    }

    fun papi(message: String?, sender: CMIUser) : String? {
        return message
            ?.replace("%job%", getjob(sender.player))
            ?.replace("%money%", getmoney(sender))
            ?.replace("%level%", getlevel(sender))
            ?.replace("%name%", sender.displayName)
            ?.let { PlaceholderAPI.setPlaceholders(sender.player, it) }
    }

    fun getjob(player: Player) : String {
        return "数据未提供"
    }

    fun getmoney(player: CMIUser) : String {
        val money = player.balance.toInt().toString()
        return if (player.hasMoney(0.0)) money else "没钱"
    }

    fun getlevel(player: CMIUser) :String {
        return player.level.toString()
    }

    @SubscribeEvent
    fun start(e: MiraiBotOnlineEvent) {
        if (config.getBoolean("启动提醒")) {
            e.bot.getGroup(config.getLong("qq")).sendMessage(config.getString("启动消息"))
        }
    }

    @SubscribeEvent
    fun stop(e: PluginDisableEvent) {
        if (e.plugin.name == "sword" && config.getBoolean("关闭提醒")) {
            bot?.getGroup(config.getLong("qq"))?.sendMessage(config.getString("关闭消息"))
        }
    }

    @SubscribeEvent
    fun help(e: MiraiGroupMessageEvent) {
        if (!config.getBoolean("qq-help")) return
        if (e.message == "#帮助" || e.message == "#help") {
            MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage(config.getString("帮助消息"))
        }
        if (e.message == "#wiki" || e.message == "#百科") MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("wiki.mcwar.cn")
        if (e.message == "#map" || e.message == "#地图") MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("map.mcwar.cn")
        if (e.message == "#官网" || e.message == "#web") MiraiBot.getBot(e.botID).getGroup(e.groupID).sendMessage("www.mcwar.cn")
    }

    @SubscribeEvent
    fun fuck(e: MiraiGroupMessageEvent) {
        if (!config.getBoolean("qq-fuck")) return
        val bot = MiraiBot.getBot(e.botID)
        if (e.message.contains("傻逼")) {
            bot.getGroup(e.groupID).sendMessage("不准说脏话哟")
            debug(bot.getGroup(e.groupID).getMember(e.senderID).permission.toString())
            if (bot.getGroup(e.groupID).getMember(e.senderID).permission < 1) {
                bot.getGroup(e.groupID).getMember(e.senderID).setMute(1)
            }
        }
    }


}