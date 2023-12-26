package com.example.leadershop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.leadershop.R
import com.squareup.picasso.Picasso

class CartAdapter(private val cartItems: MutableList<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.tvCartProductName)
        private val productPriceTextView: TextView = itemView.findViewById(R.id.tvCartProductPrice)
        private val productImageView: ImageView = itemView.findViewById(R.id.ivCartProductImage)
        private val addButton: Button = itemView.findViewById(R.id.btnCartItemplus)
        private val minusButton: Button = itemView.findViewById(R.id.btnCartItemMinus)
        private val itemNumberTextView: TextView = itemView.findViewById(R.id.tvCartItemNumber)

        fun bind(cartItem: CartItem) {
            productNameTextView.text = cartItem.name
            productPriceTextView.text = "${cartItem.price} MAD"
            try {
                Picasso.get().load(cartItem.imageResource).into(productImageView)
            } catch (e: Exception) {
            }

            addButton.setOnClickListener {
                cartItem.quantity++
                itemNumberTextView.text = cartItem.quantity.toString()
            }

            minusButton.setOnClickListener {
                if (cartItem.quantity > 1) {
                    cartItem.quantity--
                    itemNumberTextView.text = cartItem.quantity.toString()
                }
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