package com.example.leadershop


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Page_create_new_account : Fragment() {

    private lateinit var dbHelper: DataBase_login

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page_create_new_account, container, false)

        dbHelper = DataBase_login(requireContext())

        val name = view.findViewById<EditText>(R.id.name)
        val email = view.findViewById<EditText>(R.id.n_email)
        val password = view.findViewById<EditText>(R.id.n_password)
        val confirmPassword = view.findViewById<EditText>(R.id.con_password)
        val confirmButton = view.findViewById<Button>(R.id.btn_confirm)

        confirmButton.setOnClickListener {
            val nameStr = name.text.toString()
            val emailStr = email.text.toString()
            val passwordStr = password.text.toString()
            val confirmPwdStr = confirmPassword.text.toString()

            val passwordPattern = "^(?=.*[A-Za])(?=.*\\d)[A-Za-z\\d]{2,}\$".toRegex()

            fun isPasswordValid(passwordStr: String): Boolean {
                return passwordPattern.matches(passwordStr)
            }

            if (!isPasswordValid(passwordStr)) {
                Toast.makeText(requireContext(), "Invalid password format", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (nameStr.isEmpty() || emailStr.isEmpty() || passwordStr.isEmpty() || confirmPwdStr.isEmpty()) {
                    Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (passwordStr == confirmPwdStr) {
                        val textView = view.findViewById<TextView>(R.id.successText1)
                        val rotateAnimation = AnimationUtils.loadAnimation(
                            requireContext(),
                            R.anim.rotate_animation
                        )

                        textView.startAnimation(rotateAnimation)

                        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(animation: Animation?) {}
                            override fun onAnimationEnd(animation: Animation?) {
                                textView.visibility = View.VISIBLE
                                Toast.makeText(
                                    requireContext(),
                                    "Welcome $nameStr! Please sign in now.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                saveToDatabase(emailStr, passwordStr)

                                val confirm = Intent(requireContext(), MainActivity::class.java)
                                startActivity(confirm)
                            }

                            override fun onAnimationRepeat(animation: Animation?) {}
                        })
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "There is an error in your password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        return view
    }

    private fun saveToDatabase(email: String, password: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("email", email)
            put("password", password)
        }

        db.insert("info", null, values)
    }
}


