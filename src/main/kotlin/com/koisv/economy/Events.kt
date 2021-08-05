package com.koisv.economy

import com.koisv.economy.impl.EconomyImpl
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class Events : Listener {
    private val economy = EconomyImpl()
    @EventHandler
    private fun joinCreate(e: PlayerJoinEvent) {
        if (!economy.hasAccount(e.player)) {
            economy.createPlayerAccount(e.player)
        }
    }
}