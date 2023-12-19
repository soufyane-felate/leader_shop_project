package com.example.leadershop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leadershop.adapter.CartAdapter
import com.example.leadershop.adapter.CartItem

class cart_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_page)


        val productName = intent.getStringExtra("productName")?.let {
            it
        } ?: kotlin.run {
            "fgedhjkubuhd"
        }
        val productPrice = intent.getIntExtra("productPrice", 0)
        val productImage = intent.getIntExtra("productImage", 0)

        val recyclerView: RecyclerView = findViewById(R.id.rvCartItems) // Replace with your RecyclerView ID
        val cartItems = mutableListOf<CartItem>()

        val cartItem = CartItem(productName, productPrice, productImage)
        cartItems.add(cartItem)

        val adapter = CartAdapter(cartItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}

