package com.koisv.economy.impl

import com.koisv.economy.Main
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import net.milkbowl.vault.economy.EconomyResponse.ResponseType.*

class EconomyImpl : Economy {

    private val econFile = EconomyFile
    private val notFoundResponse = EconomyResponse(-404.0,-404.0,FAILURE,"계정이 존재하지 않습니다!")
    private val notImplementedResponse = EconomyResponse(-403.0,-403.0,NOT_IMPLEMENTED,"해당 기능은 존재하지 않습니다!")

    private fun successResponse(amount: Double,balance: Double) : EconomyResponse =
        EconomyResponse(amount,balance,SUCCESS,"정상 처리되었습니다!")

    override fun isEnabled(): Boolean {
        return true
    }
    override fun getName(): String {
        return "Koiconomy"
    }
    override fun hasBankSupport(): Boolean {
        return false
    }
    override fun fractionalDigits(): Int {
        return 0
    }
    override fun format(amount: Double): String {
        return "￦ $amount"
    }
    override fun currencyNamePlural(): String {
        return "원"
    }
    override fun currencyNameSingular(): String {
        return "원"
    }


    override fun hasAccount(playerName: String?): Boolean {
        return Main.userBalance.containsKey(Bukkit.getPlayer(playerName ?: "")?.uniqueId)
    }
    override fun hasAccount(player: OfflinePlayer?): Boolean {
        return Main.userBalance.containsKey(player?.uniqueId)
    }
    override fun hasAccount(playerName: String?, worldName: String?): Boolean {
        return false
    }
    override fun hasAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        return false
    }


    override fun getBalance(playerName: String?): Double {
        return Main.userBalance[Bukkit.getPlayer(playerName ?: "")?.uniqueId] ?: -404.0
    }
    override fun getBalance(player: OfflinePlayer?): Double {
        return Main.userBalance[player?.uniqueId] ?: -404.0
    }
    override fun getBalance(playerName: String?, world: String?): Double {
        return -404.0
    }
    override fun getBalance(player: OfflinePlayer?, world: String?): Double {
        return -404.0
    }


    override fun has(playerName: String?, amount: Double): Boolean {
        return (Main.userBalance[Bukkit.getPlayer(playerName ?: "")?.uniqueId] ?: -404.0) >= amount
    }
    override fun has(player: OfflinePlayer?, amount: Double): Boolean {
        return (Main.userBalance[player?.uniqueId] ?: -404.0) >= amount
    }
    override fun has(playerName: String?, worldName: String?, amount: Double): Boolean {
        return false
    }
    override fun has(player: OfflinePlayer?, worldName: String?, amount: Double): Boolean {
        return false
    }


    override fun withdrawPlayer(playerName: String?, amount: Double): EconomyResponse {
        val pid = Bukkit.getPlayer(playerName ?: "")?.uniqueId
        val bal = Main.userBalance[pid]
        econFile.save()
        return if (pid == null) notFoundResponse
        else {
            if (bal != null) {
                Main.userBalance[pid] = bal - amount
                successResponse(amount,bal)
            } else notFoundResponse
        }
    }
    override fun withdrawPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        val pid = player?.uniqueId
        val bal = Main.userBalance[pid]
        econFile.save()
        return if (pid == null) notFoundResponse
        else {
            if (bal != null) {
                Main.userBalance[pid] = bal - amount
                successResponse(amount,bal)
            } else notFoundResponse
        }
    }
    override fun withdrawPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        return notImplementedResponse
    }
    override fun withdrawPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        return notImplementedResponse
    }


    override fun depositPlayer(playerName: String?, amount: Double): EconomyResponse {
        val pid = Bukkit.getPlayer(playerName ?: "")?.uniqueId
        val bal = Main.userBalance[pid]
        econFile.save()
        return if (pid == null) notFoundResponse
        else {
            if (bal != null) {
                Main.userBalance[pid] = bal + amount
                successResponse(amount,bal)
            } else notFoundResponse
        }
    }
    override fun depositPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        val pid = player?.uniqueId
        val bal = Main.userBalance[pid]
        econFile.save()
        return if (pid == null) notFoundResponse
        else {
            if (bal != null) {
                Main.userBalance[pid] = bal + amount
                successResponse(amount,bal)
            } else notFoundResponse
        }
    }
    override fun depositPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        return notImplementedResponse
    }
    override fun depositPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        return notImplementedResponse
    }


    override fun createBank(name: String?, player: String?): EconomyResponse {
        return notImplementedResponse
    }
    override fun createBank(name: String?, player: OfflinePlayer?): EconomyResponse {
        return notImplementedResponse
    }
    override fun deleteBank(name: String?): EconomyResponse {
        return notImplementedResponse
    }
    override fun bankBalance(name: String?): EconomyResponse {
        return notImplementedResponse
    }
    override fun bankHas(name: String?, amount: Double): EconomyResponse {
        return notImplementedResponse
    }
    override fun bankWithdraw(name: String?, amount: Double): EconomyResponse {
        return notImplementedResponse
    }
    override fun bankDeposit(name: String?, amount: Double): EconomyResponse {
        return notImplementedResponse
    }
    override fun isBankOwner(name: String?, playerName: String?): EconomyResponse {
        return notImplementedResponse
    }
    override fun isBankOwner(name: String?, player: OfflinePlayer?): EconomyResponse {
        return notImplementedResponse
    }
    override fun isBankMember(name: String?, playerName: String?): EconomyResponse {
        return notImplementedResponse
    }
    override fun isBankMember(name: String?, player: OfflinePlayer?): EconomyResponse {
        return notImplementedResponse
    }
    override fun getBanks(): MutableList<String> {
        return mutableListOf("Not yet implemented")
    }

    override fun createPlayerAccount(playerName: String?): Boolean {
        val pid = Bukkit.getPlayer(playerName ?: "")?.uniqueId
        val start = Main.instance.config.getDouble("startBal")
        return if (pid != null) {
            Main.userBalance[pid] = start
            econFile.save()
        } else false
    }
    override fun createPlayerAccount(player: OfflinePlayer?): Boolean {
        val pid = player?.uniqueId
        val start = Main.instance.config.getDouble("startBal")
        return if (pid != null) {
            Main.userBalance[pid] = start
            econFile.save()
        } else false
    }
    override fun createPlayerAccount(playerName: String?, worldName: String?): Boolean {
        return false
    }
    override fun createPlayerAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        return false
    }
}