package com.example.leadershop


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class Fr_client_login : Fragment() {

    private lateinit var database: DataBase_login

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fr_client_login, container, false)

        database = DataBase_login(requireContext())
        val name = view.findViewById<EditText>(R.id.name)
        val admin = view.findViewById<ImageView>(R.id.admin)
        val email = view.findViewById<EditText>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)
        val newAcc = view.findViewById<TextView>(R.id.new_acc)
        val signIn = view.findViewById<Button>(R.id.sign_in)

        admin.setOnClickListener {
            val i = Intent(requireContext(), page_admin::class.java)
            startActivity(i)
        }
        password.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= password.right - password.compoundDrawables[2].bounds.width()) {
                    togglePasswordVisibility(password)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

        newAcc.setOnClickListener {
            val frClientLogin2 = Page_create_new_account()
            parentFragmentManager.beginTransaction().replace(R.id.frg1, frClientLogin2)
                .addToBackStack(null).commit()
        }

        signIn.setOnClickListener {
            // val nameStr = name.text.toString()
            val emailStr = email.text.toString()
            val passwordStr = password.text.toString()

            if (emailStr.isEmpty() || passwordStr.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please fill all the required fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (database.checkLogin(emailStr, passwordStr)) {
                    val textView = view.findViewById<TextView>(R.id.successText)
                    val rotateAnimation =
                        AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_animation)

                    textView.startAnimation(rotateAnimation)

                    rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {}
                        override fun onAnimationEnd(animation: Animation?) {
                            textView.visibility = View.VISIBLE
                            Toast.makeText(
                                requireContext(),
                                "Login Successful ",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(requireContext(), store::class.java)
                            startActivity(intent)
                        }

                        override fun onAnimationRepeat(animation: Animation?) {}
                    })
                } else {
                    val textView = view.findViewById<TextView>(R.id.successText)
                    val rotateAnimation =
                        AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_animation)

                    textView.startAnimation(rotateAnimation)

                    rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {}
                        override fun onAnimationEnd(animation: Animation?) {
                            textView.visibility = View.VISIBLE
                            Toast.makeText(
                                requireContext(),
                                "Email or Login Failed",
                                Toast.LENGTH_SHORT
                            ).show()
                            textView.visibility = View.INVISIBLE
                        }

                        override fun onAnimationRepeat(animation: Animation?) {}
                    })
                }
            }
        }
        return view
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
