package com.kamil184.kazanexpresstaskkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.kamil184.kazanexpresstaskkotlin.R
import com.kamil184.kazanexpresstaskkotlin.models.WalletsList


class WalletsAdapter(private val wallets: WalletsList) :
    RecyclerView.Adapter<WalletsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.wallet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wallet = wallets.wallets[position]
        holder.name.text = wallet.name
        holder.balance.text = wallet.getFormattedBalanceText()
    }

    override fun getItemCount(): Int {
        return wallets.wallets.size
    }

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val balance: TextView

        init {
            name = view.findViewById(R.id.wallet_name)
            balance = view.findViewById(R.id.wallet_balance)
        }
    }
}