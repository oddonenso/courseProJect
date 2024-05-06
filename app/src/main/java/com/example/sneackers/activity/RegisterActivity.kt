package com.example.sneackers.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sneackers.Adapter.User
import com.example.sneackers.Helper.DatabaseHelper
import com.example.sneackers.R
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        dbHelper = DatabaseHelper(this)

        val signUpButton = findViewById<Button>(R.id.sign_up_btn)
        signUpButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.username_et).text.toString()
            val email = findViewById<EditText>(R.id.email_et).text.toString()
            val password = findViewById<EditText>(R.id.password_et).text.toString()

            if (isValidInput(username, email, password)) {
                if (isUniqueUsername(username)) {
                    val user = User(username = username, email = email, password = password)
                    val userId = dbHelper.addUser(user)
                    if (userId != -1L) {
                        Toast.makeText(this, "Регистрация успешна!", Toast.LENGTH_SHORT).show()
                        finish() // Закрытие текущей активности и возвращение к LoginActivity
                    } else {
                        Toast.makeText(this, "Ошибка при регистрации", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Пользователь с таким именем уже существует", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isValidInput(username: String, email: String, password: String): Boolean {
        // Проверка уникальности юзернейма и почты, а также длины пароля
        val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val usernamePattern = Pattern.compile("[a-zA-Z0-9]+")
        return if (!emailPattern.matcher(email).matches()) {
            Toast.makeText(this, "Пожалуйста, введите корректный email", Toast.LENGTH_SHORT).show()
            false
        } else if (!usernamePattern.matcher(username).matches()) {
            Toast.makeText(this, "Имя пользователя может содержать только буквы и цифры", Toast.LENGTH_SHORT).show()
            false
        } else if (password.length < 6) {
            Toast.makeText(this, "Пароль должен содержать не менее 6 символов", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun isUniqueUsername(username: String): Boolean {
        // Проверка уникальности юзернейма
        return dbHelper.getUserByUsername(username) == null
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}
