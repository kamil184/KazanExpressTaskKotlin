package com.kamil184.kazanexpresstaskkotlin.models

import com.squareup.moshi.Json
import java.text.DecimalFormat

data class Wallet (
    @Json(name = "wallet_name")
    val name: String,
    val balance:Double){

    fun getFormattedBalanceText(): String = DecimalFormat("#.####################").format(balance)

}
