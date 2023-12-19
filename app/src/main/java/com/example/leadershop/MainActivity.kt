package com.example.leadershop


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leadershop.Fr_client_login

class MainActivity : AppCompatActivity() {
    //private lateinit var database: DataBase_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frClientLogin = Fr_client_login()
        supportFragmentManager.beginTransaction().replace(R.id.frg1, frClientLogin).commit()
        //  val frClientLogin2 = page_create_new_account()
        //   supportFragmentManager.beginTransaction().replace(R.id.frg1, frClientLogin2).commit()

    }
}