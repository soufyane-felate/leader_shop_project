package com.example.leadershop

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leadershop.adapter.CartAdapter
import com.example.leadershop.adapter.CartItem
import com.example.leadershop.adapter.CartListener
import com.example.leadershop.adapter.CartManager

class cart_page : AppCompatActivity(), CartListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var totalPriceTextView: TextView
    private var totalPrice = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_page)

        recyclerView = findViewById(R.id.rvCartItems)
        totalPriceTextView = findViewById(R.id.tvLastTotalPrice)

        val adapter = CartAdapter(CartManager.cartItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        CartManager.addCartListener(this)

        calculateTotalPrice()
    }

    override fun onDestroy() {
        super.onDestroy()
        CartManager.removeCartListener(this)
    }

    override fun onCartItemAdded(price: Double) {
        totalPrice += price
        totalPriceTextView.text = "Total Price: ₹$totalPrice"
    }

    private fun calculateTotalPrice() {
        totalPrice = CartManager.cartItems.sumByDouble { it.price * it.quantity }
        totalPriceTextView.text = " $totalPrice MAD"
    }
}