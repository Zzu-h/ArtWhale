package com.capstone.artwhale.presentation.register.music.ai

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.databinding.FragmentAiAlbumSelectBinding
import com.capstone.artwhale.presentation.common.BaseFragment
import com.capstone.artwhale.presentation.register.music.MusicRegisterViewModel
import com.capstone.artwhale.presentation.register.music.ai.adapter.AiImageRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AIAlbumSelectFragment :
    BaseFragment<FragmentAiAlbumSelectBinding>(FragmentAiAlbumSelectBinding::inflate) {

    private val viewModel by activityViewModels<MusicRegisterViewModel>()
    private lateinit var aiImageRVAdapter: AiImageRVAdapter

    override fun initAfterBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initRecyclerView()
        initObserver()
    }

    private fun clickImage(url: String) {

    }

    private fun initRecyclerView() {
        aiImageRVAdapter = AiImageRVAdapter().apply { setCallBack { clickImage(it) } }
        binding.rvAiImage.adapter = aiImageRVAdapter
    }

    private fun initObserver() {
        viewModel.aiAlbumImageList.onEach {
            aiImageRVAdapter.submitList(it)
        }.launchIn(this.lifecycleScope)
    }
}