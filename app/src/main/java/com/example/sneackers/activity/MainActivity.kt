package com.example.sneackers.activity

import BrandAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.sneackers.Adapter.PopularAdapter
import com.example.sneackers.Adapter.SliderAdapter
import com.example.sneackers.Model.BrandModel
import com.example.sneackers.Model.SliderModel
import com.example.sneackers.ViewModel.MainViewModel
import com.example.sneackers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var brandAdapter: BrandAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получение логина пользователя из Intent
        val userLogin = intent.getStringExtra("USER_LOGIN")
        // Установка логина пользователя в textView10
        binding.textView10.text = userLogin

        initBanner()
        initBrand()
        initPopular()
        initBottomMenu()

        // Обработчик нажатия на "Мои заказы"
        binding.textView17.setOnClickListener {
            val intent = Intent(this, MyZakaz::class.java)
            startActivity(intent)
        }
    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }

        // Обработчик нажатия на "Профиль"
        binding.textView16.setOnClickListener {
            openProfileActivity()
        }
    }

    private fun openProfileActivity() {
        // Получение логина пользователя из предыдущего экрана
        val userLogin = intent.getStringExtra("USER_LOGIN")
        // Создание интента для перехода в ProfileActivity
        val intent = Intent(this, ProfileActivity::class.java)
        // Передача логина пользователя в ProfileActivity
        intent.putExtra("USER_EMAIL", userLogin)
        // Запуск ProfileActivity
        startActivity(intent)
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer { items->
            banners(items)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanners()
    }

    private fun banners(images: List<SliderModel>) {
        binding.viewpagerSlider.adapter = SliderAdapter(images, binding.viewpagerSlider)
        binding.viewpagerSlider.clipToPadding = false
        binding.viewpagerSlider.clipChildren = false
        binding.viewpagerSlider.offscreenPageLimit = 3
        binding.viewpagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewpagerSlider.setPageTransformer(compositePageTransformer)
        if(images.size > 1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewpagerSlider)
        }
    }

    private fun initBrand() {
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer { brands ->
            binding.viewBrand.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            brandAdapter = BrandAdapter(brands, object : BrandAdapter.OnBrandSelectedListener {
                override fun onBrandSelected(brand: BrandModel) {
                    val filteredItems = viewModel.popular.value?.filter { it.title == brand.title }
                    filteredItems?.let {
                        binding.viewPolular.layoutManager = GridLayoutManager(this@MainActivity, 2)
                        binding.viewPolular.adapter = PopularAdapter(it.toMutableList())
                    }
                }

                override fun onResetSelection() {
                    binding.viewPolular.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    binding.viewPolular.adapter = PopularAdapter(viewModel.popular.value?.toMutableList() ?: mutableListOf())
                }
            })
            binding.viewBrand.adapter = brandAdapter
            binding.progressBarBrand.visibility = View.GONE
        })
        viewModel.loadBrand()
    }


    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.viewPolular.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.viewPolular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        })
        viewModel.loadPopular()
    }
}
