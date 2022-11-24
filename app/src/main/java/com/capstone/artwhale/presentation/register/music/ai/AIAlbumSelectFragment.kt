package com.capstone.artwhale.presentation.register.music.ai

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.databinding.FragmentAiAlbumSelectBinding
import com.capstone.artwhale.presentation.common.BaseFragment
import com.capstone.artwhale.presentation.register.music.MusicRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AIAlbumSelectFragment :
    BaseFragment<FragmentAiAlbumSelectBinding>(FragmentAiAlbumSelectBinding::inflate) {

    private val viewModel by activityViewModels<MusicRegisterViewModel>()

    override fun initAfterBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initObserver()
    }

    private fun initObserver() {
        viewModel.aiAlbumImageList.onEach {

        }.launchIn(this.lifecycleScope)
    }
}