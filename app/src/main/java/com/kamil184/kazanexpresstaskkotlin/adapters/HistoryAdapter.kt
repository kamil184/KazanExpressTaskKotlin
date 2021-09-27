package com.kamil184.kazanexpresstaskkotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kamil184.kazanexpresstaskkotlin.R
import com.kamil184.kazanexpresstaskkotlin.databinding.TransactionBinding
import com.kamil184.kazanexpresstaskkotlin.models.Transaction
import com.kamil184.kazanexpresstaskkotlin.models.TransactionsList

class HistoryAdapter(var transactionsList: TransactionsList?) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: TransactionBinding =
            DataBindingUtil.inflate(inflater, R.layout.transaction, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(transactionsList!!.transactions[position])
    }

    override fun getItemCount(): Int {
        if(transactionsList == null) return 0
        return transactionsList!!.transactions.size
    }

    class ViewHolder(private val binding: TransactionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {
            binding.transaction = transaction
            binding.executePendingBindings()
        }
    }
}