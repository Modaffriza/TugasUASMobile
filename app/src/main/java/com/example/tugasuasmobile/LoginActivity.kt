package com.example.tugasuasmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tugasuasmobile.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager.getInstance(this)

        // Periksa apakah pengguna sudah login
        if (prefManager.isLoggedIn()) {
            navigateToMain()
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // Logika login sederhana
            if (username == "admin" && password == "password") {
                prefManager.setLoggedIn(true) // Tandai sebagai login
                navigateToMain()
            } else {
                binding.tvError.text = "Username atau password salah"
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Tutup LoginActivity agar tidak dapat kembali
    }
}
