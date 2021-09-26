package com.kamil184.kazanexpresstaskkotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.RecyclerView

import com.kamil184.kazanexpresstaskkotlin.R
import com.kamil184.kazanexpresstaskkotlin.models.Transaction
import com.kamil184.kazanexpresstaskkotlin.models.TransactionsList


class HistoryAdapter(private val transactionsList: TransactionsList) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction: Transaction = transactionsList.transactions[position]
        holder.text.text = transaction.getMessage()
        holder.amount.text = transaction.getAmountText()
        if (transaction.isIncoming()) {
            holder.amount.setTextColor(ContextCompat.getColor(context!!, R.color.green))
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.arrow_down_24))
            holder.iconBackground.background = ContextCompat.getDrawable(context!!, R.drawable.green_circle)
        } else {
            holder.icon.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.arrow_right_24))
            holder.iconBackground.background = ContextCompat.getDrawable(context!!, R.drawable.red_circle)
        }
    }

    override fun getItemCount(): Int {
        return transactionsList.transactions.size
    }

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView
        val amount: TextView
        val iconBackground: FrameLayout
        val icon: ImageView

        init {
            text = view.findViewById(R.id.transaction_text)
            amount = view.findViewById(R.id.transaction_amount)
            iconBackground = view.findViewById(R.id.transaction_icon_background)
            icon = view.findViewById(R.id.transaction_icon)
        }
    }
}