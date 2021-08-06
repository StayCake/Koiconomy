package com.koisv.economy.commands

import com.koisv.economy.Main
import com.koisv.economy.impl.EconomyImpl
import hazae41.minecraft.kutils.bukkit.msg
import hazae41.minecraft.kutils.get
import io.github.monun.kommand.KommandArgument.Companion.player
import io.github.monun.kommand.getValue
import io.github.monun.kommand.node.LiteralNode
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import org.bukkit.entity.Player

object Ki {
    private val econ = EconomyImpl()
    fun register(node: LiteralNode) {
        node.then("reload") {
            requires { hasPermission(4,"admin.reload") }
            executes {
                EconomyImpl.EconFile.load()
                Main.instance.config.load(Main.instance.dataFolder["config.yml"])
                if (playerOrNull != null) {
                    player.playSound(Sound.sound(Key.key("entity.player.levelup"),Sound.Source.PLAYER,1F,1F))
                    player.msg("리로드 완료!")
                } else {
                    println("리로드 완료!")
                }
            }
        }
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
                        sender.msg("$amount 원이 ${target.name} 님에게 추가되었습니다.")
                    }
                }
                then("remove") {
                    requires { hasPermission(4,"admin.eco") }
                    executes { ctx ->
                        val target : Player by ctx
                        val amount : Double by ctx
                        econ.withdrawPlayer(target,amount)
                        sender.msg("$amount 원을 ${target.name} 님에게서 삭감하였습니다.")
                    }
                }
            }
            then("reset") {
                requires { hasPermission(4,"admin.eco") }
                executes { ctx ->
                    val target : Player by ctx
                    econ.createPlayerAccount(target)
                    sender.msg("${target.name} 님의 잔고가 초기화 되었습니다.")
                }
            }
        }
    }
}