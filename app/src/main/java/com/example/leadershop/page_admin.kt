package com.example.leadershop

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.leadershop.admin.admin_option

class page_admin : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_admin)

        val name = findViewById<EditText>(R.id.name)
        val ad_email = findViewById<EditText>(R.id.ad_email)
        val ad_password = findViewById<EditText>(R.id.ad_password)
        val btn = findViewById<Button>(R.id.sel_sign_in)

        ad_password.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= ad_password.right - ad_password.compoundDrawables[2].bounds.width()) {
                    togglePasswordVisibility(ad_password)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

        btn.setOnClickListener {
            val ad_emailStr = ad_email.text.toString()
            val ad_passStr = ad_password.text.toString()

            if (ad_emailStr == "1" && ad_passStr == "1") {
                Toast.makeText(applicationContext, "Login Successful123", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, admin_option::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Login Failed123", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun togglePasswordVisibility(passwordField: EditText) {
        val selection = passwordField.selectionEnd
        if (passwordField.transformationMethod == PasswordTransformationMethod.getInstance()) {
            passwordField.transformationMethod = HideReturnsTransformationMethod.getInstance()
            passwordField.setCompoundDrawablesWithIntrinsicBounds(
                0, 0, R.drawable.ic_baseline_visibility_24, 0
            )
        } else {
            passwordField.transformationMethod = PasswordTransformationMethod.getInstance()
            passwordField.setCompoundDrawablesWithIntrinsicBounds(
                0, 0, R.drawable.baseline_disabled_visible_24, 0
            )
        }
        passwordField.setSelection(selection)
    }
}
