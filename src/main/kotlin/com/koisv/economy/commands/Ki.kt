package com.koisv.economy.commands

import com.koisv.economy.impl.EconomyImpl
import hazae41.minecraft.kutils.bukkit.msg
import io.github.monun.kommand.KommandArgument.Companion.player
import io.github.monun.kommand.getValue
import io.github.monun.kommand.node.LiteralNode
import org.bukkit.entity.Player

object Ki {
    private val econ = EconomyImpl()
    fun register(node: LiteralNode) {
        node.then("target" to player()) {
            requires { hasPermission(4,"admin.eco") }
            executes { ctx ->
                val target : Player by ctx
                sender.msg("${target.name} : ${econ.getBalance(target)}")
            }
            then("amount" to double(0.0, Double.MAX_VALUE)) {
                then("add") {
                    requires { hasPermission(4,"admin.eco") }
                    executes { ctx ->
                        val target : Player by ctx
                        val amount : Double by ctx
                        econ.depositPlayer(target,amount)
                    }
                }
                then("remove") {
                    requires { hasPermission(4,"admin.eco") }
                    executes { ctx ->
                        val target : Player by ctx
                        val amount : Double by ctx
                        econ.withdrawPlayer(target,amount)
                    }
                }
            }
            then("reset") {
                requires { hasPermission(4,"admin.eco") }
                executes { ctx ->
                    val target : Player by ctx
                    econ.createPlayerAccount(target)
                }
            }
        }
    }
}