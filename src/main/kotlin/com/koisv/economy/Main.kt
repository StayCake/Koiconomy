package com.koisv.economy

import com.koisv.economy.commands.Ki
import com.koisv.economy.impl.EconomyFile
import com.koisv.economy.vault.VaultHook
import hazae41.minecraft.kutils.get
import io.github.monun.kommand.kommand
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

class Main : JavaPlugin() {

    companion object {
        lateinit var instance : Plugin
            private set
        lateinit var balanceData : YamlConfiguration
            private set
        lateinit var balanceLoc : File
            private set
        lateinit var userBalance : HashMap<UUID,Double>
    }

    override fun onEnable() {
        println(String.format("[%s] - 가동 시작!", description.name))
        saveDefaultConfig()

        instance = this
        balanceLoc = dataFolder["balance.yml"]
        balanceData = YamlConfiguration.loadConfiguration(balanceLoc)
        EconomyFile.load()
        VaultHook().hook()
        server.pluginManager.registerEvents(Events(), this)
        kommand {
            register("ki") {
                Ki.register(this)
            }
        }
    }

    override fun onDisable() {
        EconomyFile.save()
        VaultHook().unhook()
        saveConfig()
        println(String.format("[%s] - 가동 중지.", description.name))
    }
}