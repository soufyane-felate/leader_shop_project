package com.example.leadershop


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leadershop.admin.CustomAdapter
import com.example.leadershop.admin.Customers_Horozantal_Adapter
import com.example.leadershop.admin.DB_product
import com.example.leadershop.admin.Product_ad
import com.example.leadershop.info_product.RecyclerItemClickListener
import com.example.leadershop.info_product.product
import com.example.leadershop.info_product.search_page
import com.example.leadershop.menu.cart
import com.example.leadershop.menu.favorite
import com.example.leadershop.menu.profile
import com.google.android.material.bottomnavigation.BottomNavigationView

class store : AppCompatActivity() {
    private lateinit var productList: MutableList<Product_ad>
    private lateinit var adapter1: CustomAdapter
    private lateinit var adapter2: Customers_Horozantal_Adapter
    private lateinit var db: DB_product

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        val bnv_main = findViewById<BottomNavigationView>(R.id.bnv_main)

        bnv_main.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.m_profile -> {
                    val pro = Intent(this, profile::class.java)
                    startActivity(pro)
                    true
                }

                R.id.m_cart -> {
                    val cart1 = Intent(this, cart_page::class.java)
                    startActivity(cart1)
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

        val search = findViewById<EditText>(R.id.search_store)
        search.setOnClickListener {
            val in_sear = Intent(this, search_page::class.java)
            startActivity(in_sear)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerView1)
        val recyclerView2 = findViewById<RecyclerView>(R.id.RecyclerView2)

        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                this, recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val selectedItem = productList[position]

                        val intent = Intent(this@store, product::class.java).apply {
                            putExtra("productName", selectedItem.name)
                            putExtra("productDescription", selectedItem.description)
                            putExtra("productPrice", selectedItem.price)
                            putExtra("productImage", selectedItem.img)
                        }
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                           //////////

                    }
                })
        )

        recyclerView2.addOnItemTouchListener(
            RecyclerItemClickListener(
                this, recyclerView2,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val selectedItem = productList[position]

                        val intent = Intent(this@store, product::class.java).apply {
                            putExtra("productName", selectedItem.name)
                            putExtra("productDescription", selectedItem.description)
                            putExtra("productPrice", selectedItem.price)
                            putExtra("productImage", selectedItem.img)
                        }
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        // Handle long clicks if needed
                    }
                })
        )




        recyclerView2.layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        db = DB_product(this)
        productList = db.show()
        adapter1 = CustomAdapter(this, productList)
        adapter2 = Customers_Horozantal_Adapter(this, productList)
        recyclerView.adapter = adapter2
        recyclerView2.adapter = adapter1


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
