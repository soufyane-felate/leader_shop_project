package com.example.leadershop


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class page_sign_in : AppCompatActivity() {
    private lateinit var database: DataBase_login


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_sign_in)

        database = DataBase_login(this)

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signInButton = findViewById<Button>(R.id.sign_in)

        signInButton.setOnClickListener {
            val emailStr = email.text.toString()
            val passwordStr = password.text.toString()

            val isLoggedIn = database.checkLogin(emailStr, passwordStr)
            if (emailStr.isEmpty() || passwordStr.isEmpty()) {
                Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT)
                    .show()
            }
            if (isLoggedIn) {
                Toast.makeText(applicationContext, "Login Successful123", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, store::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Login Failed123", Toast.LENGTH_SHORT).show()
            }
        }
    }
}