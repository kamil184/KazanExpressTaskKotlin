package com.kamil184.kazanexpresstaskkotlin

import com.kamil184.kazanexpresstaskkotlin.models.TransactionsList
import com.kamil184.kazanexpresstaskkotlin.models.WalletsList
import retrofit2.Response
import retrofit2.http.GET


interface Api {
    @GET("wallets")
    suspend fun getWallets(): Response<WalletsList>

    @GET("histories")
    suspend fun getTransactions(): Response<TransactionsList>
}