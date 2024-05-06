package com.example.sneackers.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sneackers.Helper.DatabaseHelper
import com.example.sneackers.R
import com.example.sneackers.Adapter.User
import com.example.sneackers.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = DatabaseHelper(this)

        // Проверка на авторизацию
        val user = getUser()
        if (user != null) {
            // Установка имени пользователя в TextView
            binding.usernameTv.text = user.username
        } else {
            // Перенаправление на LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Обработчик для кнопки "Главная страница"
        binding.homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Обработчик для кнопки "Корзина"
        binding.cartBtn.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Обработчик для кнопки "Начать"
        binding.startBtn.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }

        // Обработчик для кнопки "Выход"
        binding.logoutBtn.setOnClickListener {
            finish() // Завершаем текущую активность, возвращаясь на предыдущую
        }
    }

    private fun getUser(): User? {
        // Получение пользователя из базы данных или другого источника данных
        // В данном примере будем получать пользователя из базы данных
        val email = intent.getStringExtra("USER_EMAIL") // Предполагается, что вы передаете email пользователя при переходе в ProfileActivity
        return email?.let { db.getUserByEmail(it) }
    }
}
