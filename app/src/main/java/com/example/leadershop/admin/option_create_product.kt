package com.example.leadershop.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.leadershop.R
import com.example.leadershop.store

/*class option_create_product : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option_create_product)


        val btn_add = findViewById<Button>(R.id.btn_add_product)

        btn_add.setOnClickListener {
            val id = findViewById<EditText>(R.id.id_product).text.toString()
            val name = findViewById<EditText>(R.id.name_product).text.toString()
            val price = findViewById<EditText>(R.id.price_product).text.toString()
            val desc = findViewById<EditText>(R.id.desc_product).text.toString()
            val img = findViewById<EditText>(R.id.img_product).text.toString()

            if (id.isEmpty() || price.isEmpty() || name.isEmpty() || desc.isEmpty() || img.isEmpty()) {
                Toast.makeText(applicationContext, "**All fields required **", Toast.LENGTH_SHORT).show()
            } else {
                val pr = price.toDouble()
                val P = Product_ad(id.toInt(), name, price.toDouble(), desc, img)

                val db = DB_product(applicationContext)
                val result = db.add(P)

                if (result != (-1).toLong()) {
                    Toast.makeText(applicationContext, "Successfully Added \uD83E\uDEE1 \uD83D\uDC4D", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this@option_create_product, store::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    })
                } else {
                    Toast.makeText(applicationContext, "Oops ! Failure  \uD83D\uDC4E ☹️", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


 */
/*class option_create_product : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option_create_product)

        val btn_add = findViewById<Button>(R.id.btn_add_product)

        btn_add.setOnClickListener {
            val id = findViewById<EditText>(R.id.id_product).text.toString()
            val name = findViewById<EditText>(R.id.name_product).text.toString()
            val price = findViewById<EditText>(R.id.price_product).text.toString()
            val desc = findViewById<EditText>(R.id.desc_product).text.toString()
            val img = findViewById<EditText>(R.id.img_product).text.toString()

            if (id.isEmpty() || price.isEmpty() || name.isEmpty() || desc.isEmpty() || img.isEmpty()) {
                Toast.makeText(applicationContext, "**All fields required **", Toast.LENGTH_SHORT).show()
            } else {
                val pr = price.toDouble()
                val P = Product_ad(id.toInt(), name, pr, desc, img)

                val db = DB_product(applicationContext)
                val result = db.add(P)

                if (result != (-1).toLong()) {
                    Toast.makeText(applicationContext, "Successfully Added \uD83E\uDEE1 \uD83D\uDC4D", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@option_create_product, store::class.java)
                    intent.putExtra("ID", P.id) // استبدل P.id بالقيمة المناسبة
                    intent.putExtra("Name", P.name)
                    intent.putExtra("Price", P.price)
                    intent.putExtra("Desc", P.description)
                    intent.putExtra("Img", P.img)
                    startActivity(intent)
                    startActivity(Intent(this@option_create_product, store::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    })
                } else {
                    Toast.makeText(applicationContext, "Oops ! Failure  \uD83D\uDC4E ☹️", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

 */
class option_create_product : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option_create_product)

        val btn_add = findViewById<Button>(R.id.btn_add_product)

        btn_add.setOnClickListener {
            val id = findViewById<EditText>(R.id.id_product).text.toString()
            val name = findViewById<EditText>(R.id.name_product).text.toString()
            val price = findViewById<EditText>(R.id.price_product).text.toString()
            val desc = findViewById<EditText>(R.id.desc_product).text.toString()
            val img = findViewById<EditText>(R.id.img_product).text.toString()

            if (id.isEmpty() || price.isEmpty() || name.isEmpty() || desc.isEmpty() || img.isEmpty()) {
                Toast.makeText(applicationContext, "**All fields required **", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val pr = price.toDouble()
                val P = Product_ad(id.toInt(), name, pr, desc, img)

                val db = DB_product(applicationContext)
                val result = db.add(P)

                if (result != (-1).toLong()) {
                    Toast.makeText(
                        applicationContext,
                        "Successfully Added \uD83E\uDEE1 \uD83D\uDC4D",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@option_create_product, store::class.java)
                    intent.putExtra("ID", P.id)
                    intent.putExtra("ID", P.id)
                    intent.putExtra("Name", P.name)
                    intent.putExtra("Price", P.price)
                    intent.putExtra("Desc", P.description)
                    intent.putExtra("Img", P.img)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Oops ! Failure  \uD83D\uDC4E ☹️",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

