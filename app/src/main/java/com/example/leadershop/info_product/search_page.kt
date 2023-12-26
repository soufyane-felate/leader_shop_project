package com.example.leadershop.info_product

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leadershop.R
import com.example.leadershop.adapter.SearchProductAdapter
import com.example.leadershop.admin.DB_product
import com.example.leadershop.admin.Product_ad
import com.example.leadershop.cart_page
import com.example.leadershop.menu.favorite
import com.example.leadershop.menu.profile
import com.example.leadershop.store
import com.google.android.material.bottomnavigation.BottomNavigationView

class search_page : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var freeDeliverySwitch: Switch
    private lateinit var dbProduct: DB_product

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_page)

        recyclerView = findViewById(R.id.recyclerSearch)
        searchEditText = findViewById(R.id.search_store)
        freeDeliverySwitch = findViewById(R.id.free_delivery_search)

        dbProduct = DB_product(this)
        val initialProducts = dbProduct.show()

        val adapter = SearchProductAdapter(initialProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        freeDeliverySwitch.setOnCheckedChangeListener { _, isChecked ->
            val filteredProducts = if (isChecked) {
                initialProducts.filter { it.free_d }
            } else {
                initialProducts
            }
            adapter.updateList(filteredProducts.toMutableList())
        }


        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val searchQuery: String = s.toString().trim()
                val searchResults: MutableList<Product_ad> =
                    dbProduct.searchProductsByName(searchQuery)
                adapter.updateList(searchResults)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val bnv_main = findViewById<BottomNavigationView>(R.id.bnv_main)

        bnv_main.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.m_home -> {
                    val pro = Intent(this, store::class.java)
                    startActivity(pro)
                    true
                }

                R.id.m_cart -> {
                    val cart1 = Intent(this, cart_page::class.java)
                    startActivity(cart1)
                    true
                }

                R.id.m_profile -> {
                    val pro = Intent(this, profile::class.java)
                    startActivity(pro)
                    true
                }

                R.id.m_favorite -> {
                    val fav = Intent(this, favorite::class.java)
                    startActivity(fav)
                    true
                }

                else -> false
            }
        }
    }
}
