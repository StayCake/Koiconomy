package com.koisv.economy.commands

import com.koisv.economy.impl.EconomyImpl
import hazae41.minecraft.kutils.bukkit.msg
import io.github.monun.kommand.node.LiteralNode

object Money {
    private val econ = EconomyImpl()
    fun register(node: LiteralNode) {
        node.executes {
            sender.msg("소지금 : ${
                when (econ.getBalance(player)) {
                    -404.0 -> 0
                    else -> econ.getBalance(player).toInt()
            }} 원")
        }
    }
}