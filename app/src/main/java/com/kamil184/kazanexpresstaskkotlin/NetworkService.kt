package com.kamil184.kazanexpresstaskkotlin

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class NetworkService private constructor() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val api: Api
        get() = retrofit.create(Api::class.java)

    companion object {
        private var networkService: NetworkService? = null
        private const val BASE_URL = "http://www.amock.io/api/kamil184/"
        val instance: NetworkService?
            get() {
                if (networkService == null) {
                    networkService = NetworkService()
                }
                return networkService
            }
    }

}