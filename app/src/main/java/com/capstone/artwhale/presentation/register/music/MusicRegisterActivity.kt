package com.capstone.artwhale.presentation.register.music

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
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
        initButton()
    }

    private fun initButton() {
        with(binding) {
            ctbSub.setOnClickDefaultIcon { onBackPressed() }
            ctbSub.setOnClickAdditionalIcon { finish() }
        }
    }

    private fun initObserver() {
        viewModel.isAI.onEach {
            val text = (if (it) "AI " else "") + getString(R.string.activity_music_register)
            binding.ctbSub.setAppBarTitle(text)
        }.launchIn(this.lifecycleScope)
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_content)
        val navController = (navHostFragment as NavHostFragment).navController
        when (navController.currentDestination?.id) {
            R.id.musicUploadFragment -> finish()
            R.id.aIAlbumSelectFragment -> navController.navigate(R.id.action_music_upload)
            else -> finish()
        }
    }
}