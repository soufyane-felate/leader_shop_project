package com.example.leadershop.admin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.leadershop.Product

class DB_product(context: Context) : SQLiteOpenHelper(context, "DB_product", null, 2) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE pro_table(id INTEGER PRIMARY KEY, name TEXT, price REAL, img TEXT, description TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS pro_table")
        onCreate(db)
    }

    fun add(s: Product_ad): Long {
        val db = writableDatabase
        val value = ContentValues()
        value.put("id", s.id)
        value.put("name", s.name)
        value.put("price", s.price)
        value.put("description", s.description)
        value.put("img", s.img)
        return db.insert("pro_table", null, value)
    }

    fun show(): MutableList<Product_ad> {
        val db = this.readableDatabase
        val cu = db.rawQuery("SELECT * FROM pro_table", null)
        val list_p = mutableListOf<Product_ad>()

        if (cu != null && cu.moveToFirst()) {
            do {
                val id = cu.getInt(0)
                val name = cu.getString(1)
                val price = cu.getDouble(2)
                val desc = cu.getString(3)
                val img = cu.getString(4)

                val sm = Product_ad(id, name, price, desc, img)
                val prsm1 = Product_ad(
                    1,
                    "car1",
                    1850.0,
                    "Free express shipping\n" +
                            "Duties and taxes are guaranteed\n" +
                            "Estimated delivery in 3-8 business days\n" +
                            "\n" +
                            "Designed to be relaxed fitting. See our size & fit guide.\n" +
                            "The model is wearing a US size XS and is 177.8cm, 61cm waist, 88.9cm hips, 78.7cm bust.\n" +
                            "The details\n" +
                            "This is a medium weight cotton sweater knit - 100% organically grown cotton. Dry clean only. More on fabric & care",
                    "https://media.thereformation.com/image/upload/f_auto,q_auto,dpr_1.0/w_500,c_scale//PRD-SFCC/1312379/BLACK/1312379.2.BLACK?_s=RAABAB0"
                )
                val prsm2 = Product_ad(
                    2, "baby clothes", 2099.99, "baby clothes",
                    "https://tse4.mm.bing.net/th?id=OIP.tzDJt3YjL2VQu1_ncc3R7wHaHa&pid=Api&P=0&h=180"
                )
                val prsm3 = Product_ad(
                    3,
                    "car3",
                    3099.99,
                    "3dhcjhbjhkbfj",
                    "https://tse4.mm.bing.net/th?id=OIP.tzDJt3YjL2VQu1_ncc3R7wHaHa&pid=Api&P=0&h=180"
                )
                val prsm4 = Product_ad(
                    4,
                    "car4",
                    4099.99,
                    "4dhcjhbjhkbfj",
                    "https://tse4.mm.bing.net/th?id=OIP.tzDJt3YjL2VQu1_ncc3R7wHaHa&pid=Api&P=0&h=180"
                )
                list_p.add(prsm1)
                list_p.add(prsm2)
                list_p.add(prsm3)
                list_p.add(prsm4)


                list_p.add(sm)
            } while (cu.moveToNext())
        }
        cu?.close()
        return list_p
    }
}