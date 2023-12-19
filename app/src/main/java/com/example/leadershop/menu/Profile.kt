package com.example.leadershop.menu

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.leadershop.R

class profile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        //val logout = findViewById<TextView>(R.id.m_logout2)

        // logout.setOnClickListener {
        // val intent1 = Intent(requireContext(),page_sign_in::class.java)
        // startActivity(intent1)
        // true
        //  }


    }
}