package ru.tyurin.exchangeratecb.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.tyurin.exchangeratecb.R
import ru.tyurin.exchangeratecb.data.model.Currency


class CurrencyAdapter(private val currencyList: List<Currency>) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencyList[position]
        holder.bind(currency)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textCurrencyName: TextView = itemView.findViewById(R.id.textCurrencyName)
        private val textCurrencyValue: TextView = itemView.findViewById(R.id.textCurrencyValue)

        fun bind(currency: Currency) {
            val text = "${currency.name} ${currency.charCode}"
            textCurrencyName.text = text
            textCurrencyValue.text = currency.value.toString()
        }
    }
}
