package com.kamil184.kazanexpresstaskkotlin.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamil184.kazanexpresstaskkotlin.models.TransactionsList
import com.kamil184.kazanexpresstaskkotlin.models.WalletsList
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel(){

    private val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO

    var onWalletsError: ((String)->Unit)? = null

    private val walletsExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onWalletsError?.invoke(throwable.localizedMessage!!)
    }

    var onTransactionsError: ((String)->Unit)? = null

    private val transactionsExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onTransactionsError?.invoke(throwable.localizedMessage!!)
    }

    private val mainRepository : MainRepository = MainRepository()

    val walletsLiveData = MutableLiveData<Response<WalletsList>>()

    fun fetchWallets() : Job = CoroutineScope(coroutineContext + walletsExceptionHandler).launch {
            val wallets = mainRepository.getWallets()
            walletsLiveData.postValue(wallets)
        }


    val transactionsLiveData = MutableLiveData<Response<TransactionsList>>()

    fun fetchTransactions(){
        CoroutineScope(coroutineContext + transactionsExceptionHandler).launch {
            val wallets = mainRepository.getTransactions()
            transactionsLiveData.postValue(wallets)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()

}