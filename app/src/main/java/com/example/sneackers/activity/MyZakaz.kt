package com.example.sneackers.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sneackers.Adapter.CartAdapter
import com.example.sneackers.Helper.ChangeNumberItemsListener
import com.example.sneackers.Helper.ManagmentCart
import com.example.sneackers.databinding.ActivityMyZakazBinding

class MyZakaz : AppCompatActivity() {
    private lateinit var binding: ActivityMyZakazBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyZakazBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVariable()
        initCartList()
        calculateCart()
    }

    private fun initCartList() {
        binding.viewCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        cartAdapter = CartAdapter(managmentCart.getListCart(), this, object : ChangeNumberItemsListener {
            override fun onChanged() {
                calculateCart()
            }
        })
        binding.viewCart.adapter = cartAdapter

        binding.emptyTxt.visibility = if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
        binding.scrollView2.visibility = if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
    }

    private fun calculateCart() {
        val delivery = 100.0
        tax = Math.round((managmentCart.getTotalFee()) * 100) / 100.0
        val total = Math.round((managmentCart.getTotalFee() + delivery + tax) * 100) / 100
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100

        binding.totalFeeTxt.text = "Баллов: $itemTotal"
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun requestHelp() {
        val cartItems = managmentCart.getListCart()
        val cartItemNames = ArrayList<String>()
        for (item in cartItems) {
            // Добавляем имена товаров из списка заказов в cartItemNames
            cartItemNames.add(item.title)
        }
        if (cartItemNames.isNotEmpty()) {
            // Здесь можно добавить логику для запроса помощи
            Toast.makeText(this, "Запрос помощи отправлен", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Ваша корзина пуста", Toast.LENGTH_SHORT).show()
        }
    }
}
