package com.kamil184.kazanexpresstaskkotlin.models

import com.squareup.moshi.Json

data class TransactionsList(@Json(name = "histories")val transactions: List<Transaction>)