package com.example.leadershop.info_product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leadershop.R
import com.example.leadershop.adapter.SearchProductAdapter
import com.example.leadershop.admin.DB_product
import com.example.leadershop.admin.Product_ad


class search_page : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var dbProduct: DB_product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_page)

        recyclerView = findViewById(R.id.recyclerSearch)
        searchEditText = findViewById(R.id.search_store)
        dbProduct = DB_product(this)

        val initialProducts = dbProduct.show()
        val adapter = SearchProductAdapter(initialProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val searchQuery: String = s.toString().trim()
                val searchResults: MutableList<Product_ad> = dbProduct.searchProductsByName(searchQuery)
                adapter.updateList(searchResults)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
