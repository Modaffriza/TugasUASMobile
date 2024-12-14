package com.example.tugasuasmobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugasuasmobile.R
import com.example.tugasuasmobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager.getInstance(this)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, HomeFragment()) // Updated to use nav_host_fragment
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, HomeFragment()) // Updated to use nav_host_fragment
                        .commit()
                    true
                }
                R.id.nav_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, FavoriteFragment()) // Updated to use nav_host_fragment
                        .commit()
                    true
                }
                R.id.nav_logout -> {
                    prefManager.setLoggedIn(false)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
