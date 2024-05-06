package com.example.sneackers.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sneackers.Helper.DatabaseHelper
import com.example.sneackers.Model.UserModel
import com.example.sneackers.R

class LoginActivity : AppCompatActivity() {
    private val users = mutableListOf<UserModel>()
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbHelper = DatabaseHelper(this)

        val loginButton: Button = findViewById(R.id.login_btn)
        loginButton.setOnClickListener {
            // Получение введенных пользователем данных
            val enteredEmail = findViewById<EditText>(R.id.email_et).text.toString()
            val enteredPassword = findViewById<EditText>(R.id.password_et).text.toString()

            // Получение пользователя из базы данных по введенному email
            val user = dbHelper.getUserByEmail(enteredEmail)

            if (user != null && user.password == enteredPassword) {
                // Пользователь найден и пароль совпадает, переход на MainActivity
                val intent = Intent(this, MainActivity::class.java)
                // Передайте логин пользователя через Intent
                intent.putExtra("USER_LOGIN", enteredEmail)
                startActivity(intent)
                Toast.makeText(this, "Авторизация успешна!", Toast.LENGTH_SHORT).show()
            } else {
                // Пользователь не найден или пароль не совпадает, вывод сообщения об ошибке
                Toast.makeText(this, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработчик для перехода на RegisterActivity
        val registerTextView = findViewById<TextView>(R.id.go_to_register_activity_tv)
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadUsers() {
        // Пример добавления пользователей вручную (используйте ваш собственный способ загрузки данных)
        users.add(UserModel("oddonenso", "qasd4321", "oddonenso@gmail.com"))
        users.add(UserModel("feodor23", "feodor23", "feodor23"))
    }
}
