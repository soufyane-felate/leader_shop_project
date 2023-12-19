package com.example.leadershop.admin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.leadershop.R

class admin_option : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_option)

        val op_pro = findViewById<Button>(R.id.option_product)

        op_pro.setOnClickListener {
            val intent_pro = Intent(applicationContext, option_create_product::class.java)
            startActivity(intent_pro)
        }
    }
}