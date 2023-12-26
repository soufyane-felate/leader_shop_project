package com.example.leadershop.adapter


data class CartItem(
    val name: String,
    val price: Double,
    val imageResource: String,
    var quantity: Int = 1,
)




