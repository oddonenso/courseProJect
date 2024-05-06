package com.example.sneackers.Helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sneackers.Adapter.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UsersDB"
        private const val TABLE_USERS = "users"

        private const val KEY_ID = "id"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = ("CREATE TABLE $TABLE_USERS($KEY_ID INTEGER PRIMARY KEY, $KEY_USERNAME TEXT, $KEY_EMAIL TEXT, $KEY_PASSWORD TEXT)")
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(user: User): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USERNAME, user.username)
        values.put(KEY_EMAIL, user.email)
        values.put(KEY_PASSWORD, user.password)
        return db.insert(TABLE_USERS, null, values)
    }

    @SuppressLint("Range")
    fun getUserByEmail(email: String): User? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $KEY_EMAIL = ?"
        val cursor = db.rawQuery(query, arrayOf(email))
        var user: User? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val username = cursor.getString(cursor.getColumnIndex(KEY_USERNAME))
            val password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
            user = User(id, username, email, password)
        }
        cursor.close()
        return user
    }
    

    @SuppressLint("Range")
    fun getUserByUsername(username: String): User? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $KEY_USERNAME = ?"
        val cursor = db.rawQuery(query, arrayOf(username))
        var user: User? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
            val password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
            user = User(id, username, email, password)
        }
        cursor.close()
        return user
    }
}
