package com.capstone.artwhale.presentation.register.album

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.capstone.artwhale.databinding.ActivityAlbumRegisterBinding

class AlbumRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityAlbumRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}