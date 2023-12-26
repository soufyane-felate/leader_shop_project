package com.example.leadershop.info_product

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
//import com.example.leadershop.cart_page
import com.example.leadershop.R
import com.example.leadershop.adapter.CartItem
import com.example.leadershop.adapter.CartManager
import com.example.leadershop.cart_page
import com.squareup.picasso.Picasso

class product : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val productName = intent.getStringExtra("productName")
        val productDescription = intent.getStringExtra("productDescription")
        val productPrice = intent.getDoubleExtra("productPrice", 0.0)
        val productImage = intent.getStringExtra("productImage")
        val productDelivery = intent.getBooleanExtra("productdelivery", false)

        val productNameTextView = findViewById<TextView>(R.id.product_name)
        val productDescTextView = findViewById<TextView>(R.id.product_desc)
        val productPriceTextView = findViewById<TextView>(R.id.product_price)
        val sliderImageView = findViewById<ImageView>(R.id.slider_img)
        val productDeliveryTextView = findViewById<TextView>(R.id.delivery)

        productNameTextView.text = productName
        productDescTextView.text = productDescription
        productPriceTextView.text = "$productPrice MAD"
        productDeliveryTextView.text = if (productDelivery) "Free Delivery" else "Delivery Not Free"

        val drawableId =
            if (productDelivery) R.drawable.ic_free_delivery else R.drawable.ic_delivery_charges
        productDeliveryTextView.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0)

        val colorId = if (productDelivery) R.color.green else R.color.red
        productDeliveryTextView.setTextColor(ContextCompat.getColor(this, colorId))

        Picasso.get().load(productImage).into(sliderImageView)


        val addToCartButton = findViewById<Button>(R.id.addToCart)
        addToCartButton.setOnClickListener {
            val productName = intent.getStringExtra("productName") ?: "Default Name"
            val productPrice = intent.getDoubleExtra("productPrice", 0.0)
            val productImage = intent.getStringExtra("productImage") ?: ""

            val cartItem = CartItem(productName, productPrice, productImage)
            CartManager.addToCart(cartItem)
            Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show()


        }

    }

}
