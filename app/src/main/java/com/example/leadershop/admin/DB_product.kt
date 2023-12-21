package com.example.leadershop.admin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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
                list_p.add(sm)
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

                val product = Product_ad(id, productName, price, description, img)
                searchResults.add(product)
            } while (cu.moveToNext())
        }
        cu?.close()
        return searchResults
    }
}
