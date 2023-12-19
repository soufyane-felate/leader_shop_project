package com.example.leadershop

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase_login(context: Context) : SQLiteOpenHelper(context, "DB_SHOP", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE info(email TEXT ,password TEXT ) ")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS info")
    }

    fun addData(email: String, password: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("email", email)
        contentValues.put("password", password)

        return db.insert("info", null, contentValues)
    }

    fun checkLogin(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM info WHERE email=? AND password=?"
        val cursor = db.rawQuery(query, arrayOf(email, password))
        return cursor.moveToFirst()
    }


}