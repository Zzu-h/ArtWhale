package com.capstone.artwhale.presentation.register.album

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.capstone.artwhale.databinding.ActivityAlbumRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumRegisterBinding
    private val viewModel by viewModels<AlbumRegisterViewModel>()

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            try {
                uri?.apply { viewModel.setAlbumImage(uri) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityAlbumRegisterBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initButton()
        initSpinner()
    }

    private fun initSpinner() {}

    private fun initButton() {
        with(binding) {
            ivAlbumThumbnail.setOnClickListener { getContent.launch("image/*") }
            cvRegister.setOnClickListener {}
            ctbSub.setOnClickDefaultIcon { finish() }
        }
    }
}