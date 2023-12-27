package com.example.leadershop.admin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB_product(context: Context) : SQLiteOpenHelper(context, "DB_product", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE pro_table(id INTEGER PRIMARY KEY, name TEXT, price REAL, img TEXT, description TEXT, free_d INTEGER)")
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
        value.put("free_d", if (s.free_d) 1 else 0)
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
                val free_d = cu.getInt(5) == 1

                val product = Product_ad(id, name, price, desc, img, free_d)
                val pr2 = Product_ad(
                    101,
                    " Pro 6 Wireless",
                    100.00,
                    " Pro 6 TWS Wireless Bluetooth Earphones Headphones Mini Fone Earphone Stereo Sport Headset For Xiaomi Android Earbuds",
                    "https://ae01.alicdn.com/kf/Sd98fafd8788e4c50a0293eebfaa5fbffI/PRO-6-Wireless-Bluetooth-Headset-5-0-Bluetooth-Protocol-Earplug-Type-Strong-Endurance-Gaming-Competitive-Headphones.png_350x350xz.png_.webp",
                    true
                )
                val pr3 = Product_ad(
                    110,
                    "Original Pro 6",
                    100.00,
                    "Original Pro 6 TWS Wireless Bluetooth Earphones Headphones Mini Fone Earphone Stereo Sport Headset For Xiaomi Android Earbuds",
                    "https://ae01.alicdn.com/kf/Se9889fea78d44b7eadccd336e9aba4a1w/Original-Pro-6-TWS-Wireless-Bluetooth-Earphones-Headphones-Mini-Fone-Earphone-Stereo-Sport-Headset-For-Xiaomi.jpg_350x350xz.jpg_.webp",
                    true
                )
                list_p.add(product)
                list_p.add(pr2)
            } while (cu.moveToNext())
        }
        cu?.close()
        return list_p
    }

    fun searchProductsByName(name: String): MutableList<Product_ad> {
        val db = this.readableDatabase
        val cu = db.rawQuery("SELECT * FROM pro_table WHERE name LIKE '%$name%'", null)
        val searchResults = mutableListOf<Product_ad>()

        if (cu != null && cu.moveToFirst()) {
            do {
                val id = cu.getInt(0)
                val productName = cu.getString(1)
                val price = cu.getDouble(2)
                val description = cu.getString(3)
                val img = cu.getString(4)
                val free_d = cu.getInt(5) == 1

                val product = Product_ad(id, productName, price, description, img, free_d)
                searchResults.add(product)
            } while (cu.moveToNext())
        }
        cu?.close()
        return searchResults
    }
}
