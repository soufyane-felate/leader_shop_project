package com.example.leadershop.adapter

object CartManager {
    val cartItems = mutableListOf<CartItem>()
    private val cartListeners = mutableListOf<CartListener>()

    fun addToCart(cartItem: CartItem) {
        cartItems.add(cartItem)
        cartListeners.forEach { it.onCartItemAdded(cartItem.price) }
    }

    fun addCartListener(listener: CartListener) {
        cartListeners.add(listener)
    }

    fun removeCartListener(listener: CartListener) {
        cartListeners.remove(listener)
    }
}


