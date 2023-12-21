package com.example.leadershop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.leadershop.R
import com.example.leadershop.admin.Product_ad
import com.squareup.picasso.Picasso

class SearchProductAdapter(private var productList: MutableList<Product_ad>) :
    RecyclerView.Adapter<SearchProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView = itemView.findViewById(R.id.searchProductname)
        private val productPrice: TextView = itemView.findViewById(R.id.searchProductprice)
        private val searchProductImg: ImageView = itemView.findViewById(R.id.searchProductImg)

        fun bind(product: Product_ad) {
            productName.text = product.name
            productPrice.text = product.price.toString()
            try {
                Picasso.get().load(product.img).into(searchProductImg)
            } catch (e: Exception) {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateList(newList: MutableList<Product_ad>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }
}
