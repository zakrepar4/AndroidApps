package com.example.cryptoassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassignment.model.CoinController

class CoinRecyclerAdapter (private val controller : CoinController,
                           private val delegate: CoinRecyclerAdapterDelegate): RecyclerView.Adapter<CoinRecyclerAdapter.ViewHolder>() {

    interface CoinRecyclerAdapterDelegate {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(private val coinItemView: View, private val delegate: CoinRecyclerAdapterDelegate) : RecyclerView.ViewHolder(coinItemView) {

        val textViewLogo: TextView = coinItemView.findViewById<TextView>(R.id.textViewLogo)
        val textViewID: TextView = coinItemView.findViewById<TextView>(R.id.textViewID)
        val textViewPrice: TextView = coinItemView.findViewById<TextView>(R.id.textViewPrice)
        val textViewSymbol: TextView = coinItemView.findViewById<TextView>(R.id.textViewSymbol)

        init {
            coinItemView.setOnClickListener {
                delegate.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val coinItemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)

        return ViewHolder(coinItemView, delegate)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val coin = controller.getCoinAtPosition(position)

        if (coin != null) {
            holder.textViewLogo.text = coin.logo.orEmpty()
            holder.textViewID.text = coin.id
            holder.textViewPrice.text = coin.price.orEmpty()
            holder.textViewSymbol.text = coin.symbol.orEmpty()
        }
    }



    override fun getItemCount(): Int {
        return controller.size
    }


} // end class
