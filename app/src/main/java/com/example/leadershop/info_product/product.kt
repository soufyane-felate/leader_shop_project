package com.example.leadershop.info_product

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
//import com.example.leadershop.cart_page
import com.example.leadershop.R
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

        val productNameTextView = findViewById<TextView>(R.id.product_name)
        val productDescTextView = findViewById<TextView>(R.id.product_desc)
        val productPriceTextView = findViewById<TextView>(R.id.product_price)
        val sliderImageView = findViewById<ImageView>(R.id.slider_img)

        productNameTextView.text = productName
        productDescTextView.text = productDescription
        productPriceTextView.text = "$productPrice MAD"

        Picasso.get().load(productImage).into(sliderImageView)






        val addToCart=findViewById<Button>(R.id.addToCart)


      /*  addToCart.setOnClickListener {
            val intent = Intent(this, cart_page::class.java)

            intent.putExtra("productName", productName)
            intent.putExtra("productPrice", productPrice)
            intent.putExtra("productImage", productImage)

            startActivity(intent)
        }

       */



    }
}
