package com.capstone.artwhale.presentation.register.music

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.capstone.artwhale.databinding.ActivityMusicRegisterBinding

class MusicRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMusicRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}