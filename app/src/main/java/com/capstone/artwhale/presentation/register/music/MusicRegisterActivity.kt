package com.capstone.artwhale.presentation.register.music

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.ActivityMusicRegisterBinding
import com.capstone.artwhale.domain.model.Album
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MusicRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicRegisterBinding
    private val viewModel by viewModels<MusicRegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMusicRegisterBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val data = intent.getSerializableExtra("album") as Album?
        data?.let { viewModel.setAlbum(it) }

        initObserver()
    }

    private fun initObserver() {
        viewModel.isAI.onEach {
            val text = (if (it) "AI " else "") + getString(R.string.activity_music_register)
            binding.ctbSub.setAppBarTitle(text)
        }.launchIn(this.lifecycleScope)
    }
}