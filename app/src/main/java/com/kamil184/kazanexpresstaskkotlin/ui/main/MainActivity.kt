package com.kamil184.kazanexpresstaskkotlin.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kamil184.kazanexpresstaskkotlin.R
import com.kamil184.kazanexpresstaskkotlin.adapters.HistoryAdapter
import com.kamil184.kazanexpresstaskkotlin.adapters.WalletsAdapter
import com.kamil184.kazanexpresstaskkotlin.databinding.ActivityMainBinding
import com.kamil184.kazanexpresstaskkotlin.models.TransactionsList
import com.kamil184.kazanexpresstaskkotlin.models.WalletsList


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private var walletsList: WalletsList? = null
    private var transactionsList: TransactionsList? = null

    private lateinit var walletsAdapter: WalletsAdapter
    private lateinit var historyAdapter: HistoryAdapter

    private lateinit var walletsRecycler: RecyclerView
    private lateinit var transactionsRecycler: RecyclerView
    private lateinit var transactionPlaceholders: LinearLayout
    private lateinit var walletPlaceholders: LinearLayout
    private lateinit var walletError: TextView
    private lateinit var transactionError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        walletsRecycler = binding.wallets
        transactionsRecycler = binding.transactions
        transactionPlaceholders = binding.transactionPlaceholders
        walletPlaceholders = binding.walletPlaceholders
        walletError = binding.walletErrorText
        transactionError = binding.transactionErrorText

        binding.swipeRefreshLayout.setColorSchemeColors(
            ContextCompat.getColor(
                this,
                R.color.design_default_color_primary
            )
        )

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.onWalletsError = {
            Log.e(MainActivity::class.java.simpleName, it)
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.setRefreshing(false)
                walletError.text = getString(R.string.check_internet_connection)
                walletPlaceholders.visibility = View.GONE
                walletsRecycler.visibility = View.GONE
                walletError.visibility = View.VISIBLE
            }, 300)
        }

        viewModel.onTransactionsError = {
            Log.e(MainActivity::class.java.simpleName, it)
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.setRefreshing(false)
                transactionError.text = getString(R.string.check_internet_connection)
                transactionPlaceholders.visibility = View.GONE
                transactionsRecycler.visibility = View.GONE
                transactionError.visibility = View.VISIBLE
            }, 300)
        }

        viewModel.onRefreshListener = {
            setWallets()
            setTransactions()
        }

        binding.viewModel = viewModel

        viewModel.fetchWallets()
        viewModel.fetchTransactions()

        walletsAdapter = WalletsAdapter(walletsList)
        walletsRecycler.adapter = walletsAdapter

        viewModel.walletsLiveData.observe(this, {
            viewModel.setRefreshing(false)

            if (it.isSuccessful) {
                walletPlaceholders.visibility = View.GONE
                walletsRecycler.visibility = View.VISIBLE
                walletError.visibility = View.GONE

                walletsList = it.body()!!
                walletsAdapter.wallets = walletsList
                walletsAdapter.notifyDataSetChanged()
            } else if (it.code() == 429) {
                walletError.text = getString(R.string.too_many_requests)
                walletPlaceholders.visibility = View.GONE
                walletsRecycler.visibility = View.GONE
                walletError.visibility = View.VISIBLE
            }
        })

        historyAdapter = HistoryAdapter(transactionsList)
        transactionsRecycler.adapter = historyAdapter

        viewModel.transactionsLiveData.observe(this, {
            viewModel.setRefreshing(false)

            if (it.isSuccessful) {
                transactionPlaceholders.visibility = View.GONE
                transactionsRecycler.visibility = View.VISIBLE
                transactionError.visibility = View.GONE

                transactionsList = it.body()!!
                historyAdapter.transactionsList = transactionsList
                transactionsRecycler.adapter = historyAdapter
                historyAdapter.notifyDataSetChanged()
            } else if (it.code() == 429) {
                transactionError.text = getString(R.string.too_many_requests)
                transactionPlaceholders.visibility = View.GONE
                transactionsRecycler.visibility = View.GONE
                transactionError.visibility = View.VISIBLE
            }
        })
    }

    private fun setWallets() {
        walletPlaceholders.visibility = View.VISIBLE
        walletsRecycler.visibility = View.GONE
        walletError.visibility = View.GONE

        viewModel.fetchWallets()
    }

    private fun setTransactions() {
        transactionPlaceholders.visibility = View.VISIBLE
        transactionsRecycler.visibility = View.GONE
        transactionError.visibility = View.GONE

        viewModel.fetchTransactions()
    }

}