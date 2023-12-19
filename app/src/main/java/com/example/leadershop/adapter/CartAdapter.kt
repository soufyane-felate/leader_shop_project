package com.example.leadershop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.leadershop.R
import com.squareup.picasso.Picasso
import dagger.hilt.android.internal.Contexts

class CartAdapter(private val cartItems: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.tvCartProductName)
        private val productPriceTextView: TextView = itemView.findViewById(R.id.tvCartProductPrice)
        private val productImageView: ImageView = itemView.findViewById(R.id.ivCartProductImage)

        fun bind(cartItem: CartItem) {
            productNameTextView.text = cartItem.name
            productPriceTextView.text = "${cartItem.price} MAD"
            try {
                Picasso.get().load(cartItem.imageResource).into(productImageView)

            }catch (e:Exception){

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_product_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}


