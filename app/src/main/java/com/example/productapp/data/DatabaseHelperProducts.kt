package com.example.productapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelperProducts(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "products.db"
        const val DATABASE_VERSION = 1
        const val TABLE_PRODUCTS = "products"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_PRODUCTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_PRICE REAL NOT NULL
            )
        """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        onCreate(db)
    }

    fun insertProduct(name: String, price: Double) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_PRICE, price)
        }
        db.insert(TABLE_PRODUCTS, null, values)
        db.close()
    }

    fun getAllProducts(): List<Pair<String, Double>> {
        val productList = mutableListOf<Pair<String, Double>>()
        val db = readableDatabase
        val cursor = db.query(TABLE_PRODUCTS, arrayOf(COLUMN_NAME, COLUMN_PRICE), null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
                productList.add(name to price)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return productList
    }

    fun deleteProduct(name: String) {
        val db = writableDatabase
        db.delete(TABLE_PRODUCTS, "$COLUMN_NAME = ?", arrayOf(name))
        db.close()
    }

    fun updateProduct(oldName: String, newName: String, newPrice: Double) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, newName)
            put(COLUMN_PRICE, newPrice)
        }
        db.update(TABLE_PRODUCTS, values, "$COLUMN_NAME = ?", arrayOf(oldName))
        db.close()
    }
}
