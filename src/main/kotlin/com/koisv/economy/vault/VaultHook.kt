package com.koisv.economy.vault

import com.koisv.economy.Main
import com.koisv.economy.impl.EconomyImpl
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.ServicePriority
import net.milkbowl.vault.economy.Economy

class VaultHook {
    private val provider = EconomyImpl()

    fun hook() {
        val vault = Bukkit.getPluginManager().getPlugin("Vault")
        if (vault != null) {
            Bukkit.getServicesManager().register(Economy::class.java, provider, vault, ServicePriority.Normal)
            println(String.format("[%s] - ${ChatColor.YELLOW}Vault 연결됨!",Main.instance.description.name))
        } else {
            println(String.format("[%s] - ${ChatColor.YELLOW}Vault 감지되지 않음. 개별모드 작동 중...",Main.instance.description.name))
        }
    }

    fun unhook() {
        Bukkit.getServicesManager().unregister(Economy::class.java, provider)
        println(String.format("[%s] - ${ChatColor.YELLOW}Vault 연결 해제됨!",Main.instance.description.name))
    }
}