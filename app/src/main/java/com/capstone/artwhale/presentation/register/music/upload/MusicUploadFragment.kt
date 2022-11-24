package com.capstone.artwhale.presentation.register.music.upload

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.databinding.FragmentMusicUploadBinding
import com.capstone.artwhale.presentation.common.BaseFragment
import com.capstone.artwhale.presentation.register.music.MusicRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MusicUploadFragment :
    BaseFragment<FragmentMusicUploadBinding>(FragmentMusicUploadBinding::inflate) {

    private val viewModel by activityViewModels<MusicRegisterViewModel>()

    override fun initAfterBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initObserver()
    }

    private fun initObserver() {
        viewModel.isAI.onEach {
            val text = if (it) "Run" else "Register"
            binding.tvRegister.text = text
        }.launchIn(this.lifecycleScope)
    }
}