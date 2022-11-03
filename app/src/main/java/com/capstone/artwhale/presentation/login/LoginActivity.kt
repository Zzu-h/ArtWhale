package com.capstone.artwhale.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.artwhale.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(android.R.style.ThemeOverlay_Material_Dark)
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}