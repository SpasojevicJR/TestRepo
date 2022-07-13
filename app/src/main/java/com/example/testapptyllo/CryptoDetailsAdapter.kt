package com.example.testapptyllo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class CryptoDetailsAdapter(
    private val context: Context,
    private val cryptoDetailsList: MutableList<CryptoDetails>,

    ) : RecyclerView.Adapter<CryptoDetailsAdapter.Holder>() {

    inner class Holder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val cryptoName = itemView.findViewById<TextView>(R.id.cryptoName)
        val lastUpdated = itemView.findViewById<TextView>(R.id.last_updated)
        val cryptoPrice = itemView.findViewById<TextView>(R.id.cryptoPrice)

        fun bindCrypto(cryptoDetails: CryptoDetails) {
            val decimal = BigDecimal(cryptoDetails.price).setScale(2, RoundingMode.HALF_EVEN)
            cryptoName.text = cryptoDetails.name
            cryptoPrice.text = "Value: $decimal$"

            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val outputFormat = SimpleDateFormat("dd-MM-yyyy \n hh:mm")
            val date: Date = inputFormat.parse(cryptoDetails.lastUpdated)
            val formattedDate: String = outputFormat.format(date)
            lastUpdated.text = "Updated on:\n $formattedDate"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.crypto_details, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindCrypto(cryptoDetailsList[position])
    }

    override fun getItemCount(): Int {
        return cryptoDetailsList.count()
    }

}