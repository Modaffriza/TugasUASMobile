package com.example.tugasuasmobile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugasuasmobile.R
import com.example.tugasuasmobile.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        prefManager = PrefManager.getInstance(this)
        refresh()

        setupBottomNavigation()
        setContentView(binding.root)


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
    fun refresh(){
        val client = ApiClient.instance
        val Dpr = client.getDpr()
        Dpr.enqueue(object : Callback<List<Dpr>>{
            override fun onResponse(p0: Call<List<Dpr>>, p1: Response<List<Dpr>>) {
                prefManager.saveData(p1.body()!!)
                println(p1.body()!!.toString())
                Toast.makeText(binding.root.context, p1.body()!!.toString(), Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(p0: Call<List<Dpr>>, p1: Throwable) {
                Toast.makeText(binding.root.context, "Fetch Cupu", Toast.LENGTH_SHORT).show()
            }

        })


    }
}
