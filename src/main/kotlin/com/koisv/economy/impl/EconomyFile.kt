package com.koisv.economy.impl

import com.koisv.economy.Main
import hazae41.minecraft.kutils.bukkit.keys
import java.util.*
import kotlin.collections.HashMap

class EconomyFile {
    companion object {
        fun load() {
            if (!Main.balanceLoc.canRead()) {
                Main.balanceData.save(Main.balanceLoc)
            }
            val dataKey = Main.balanceData.keys
            val map : HashMap<UUID,Double> = HashMap()
            dataKey.forEach {
                val key = UUID.fromString(it)
                val value = Main.balanceData.getDouble(it)
                map[key] = value
            }
            Main.userBalance = map
        }
        fun save() : Boolean {
            Main.userBalance.forEach { (ID, Value) ->
                Main.balanceData.set(ID.toString(), Value)
            }
            return try {
                Main.balanceData.save(Main.balanceLoc)
                true
            } catch (e: Error) {
                println(e.stackTrace)
                false
            }
        }
    }
}