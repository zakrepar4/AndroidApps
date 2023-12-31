package com.example.cryptoassignment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassignment.model.CoinController
import com.squareup.picasso.Picasso

class CoinRecyclerAdapter (private val controller : CoinController,
                           private val delegate: CoinRecyclerAdapterDelegate): RecyclerView.Adapter<CoinRecyclerAdapter.ViewHolder>() {

    interface CoinRecyclerAdapterDelegate {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(coinItemView: View, private val delegate: CoinRecyclerAdapterDelegate) : RecyclerView.ViewHolder(coinItemView) {

        val imageViewLogo: ImageView = coinItemView.findViewById<ImageView>(R.id.imageViewLogo)
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


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val coin = controller.getCoinAtPosition(position)

        if (coin != null) {

            Picasso.get().load(coin.image?.thumb).placeholder(R.drawable.coin_default_detail_image).into(holder.imageViewLogo)
            holder.textViewID.text = coin.id
            holder.textViewPrice.text = "$" + String.format("%.2f", coin.market_data?.getPrice())
            holder.textViewSymbol.text = coin.symbol.orEmpty()
        }
    }



    override fun getItemCount(): Int {
        return controller.size
    }


} // end class



