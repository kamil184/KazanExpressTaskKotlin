package com.kamil184.kazanexpresstaskkotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kamil184.kazanexpresstaskkotlin.R
import com.kamil184.kazanexpresstaskkotlin.databinding.WalletBinding
import com.kamil184.kazanexpresstaskkotlin.models.Wallet
import com.kamil184.kazanexpresstaskkotlin.models.WalletsList

class WalletsAdapter(var wallets: WalletsList?) :
    RecyclerView.Adapter<WalletsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: WalletBinding =
            DataBindingUtil.inflate(inflater, R.layout.wallet, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(wallets!!.wallets[position])
    }

    override fun getItemCount(): Int {
        if(wallets == null) return 0
        return wallets!!.wallets.size
    }

    class ViewHolder(private val binding: WalletBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(wallet: Wallet) {
            binding.wallet = wallet
            binding.executePendingBindings()
        }
    }
}
