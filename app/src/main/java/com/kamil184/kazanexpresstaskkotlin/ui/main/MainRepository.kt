package com.kamil184.kazanexpresstaskkotlin.ui.main

import com.kamil184.kazanexpresstaskkotlin.NetworkService

class MainRepository{
    suspend fun getWallets() = NetworkService.instance!!.api.getWallets()

    suspend fun getTransactions() = NetworkService.instance!!.api.getTransactions()

}