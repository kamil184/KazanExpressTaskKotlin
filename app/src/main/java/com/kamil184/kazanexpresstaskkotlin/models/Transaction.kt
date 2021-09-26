package com.kamil184.kazanexpresstaskkotlin.models

import java.text.DecimalFormat

data class Transaction(
    val entry: String,
    val currency: String,
    val sender: String,
    val recipient: String,
    val amount: Double?,
    val balance: Double?
) {
    fun getMessage(): String {
        return if (entry == "incoming") {
            "You've received payment from $sender"
        } else "You've cashed out to $recipient"
    }

    fun getAmountText(): String {
        val decimalFormat = DecimalFormat("#.####################")
        return if (entry == "incoming") {
            "+ " + decimalFormat.format(amount).toString() + " $currency"
        } else decimalFormat.format(balance).toString() + " $currency"
    }

    fun isIncoming(): Boolean = entry == "incoming"

}
