package com.capstone.artwhale.presentation.home.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.databinding.FragmentMusicBinding
import com.capstone.artwhale.presentation.home.music.adapter.MusicChartRVAdapter
import com.capstone.artwhale.presentation.home.music.adapter.NewMusicRVAdapter
import com.capstone.artwhale.presentation.home.music.adapter.NoticeRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MusicFragment : Fragment() {

    private var _binding: FragmentMusicBinding? = null
    private val viewModel by viewModels<MusicViewModel>()

    private val binding get() = _binding!!

    private lateinit var noticeRVAdapter: NoticeRVAdapter
    private lateinit var musicChartRVAdapter: MusicChartRVAdapter
    private lateinit var newMusicRVAdapter: NewMusicRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserver()
    }

    private fun initRecyclerView() {
        noticeRVAdapter = NoticeRVAdapter()
        musicChartRVAdapter = MusicChartRVAdapter()
        newMusicRVAdapter = NewMusicRVAdapter()

        with(binding) {
            rvNotice.adapter = noticeRVAdapter
            rvChart.adapter = musicChartRVAdapter
            rvNewList.adapter = newMusicRVAdapter
        }
    }

    private fun initObserver() {
        with(viewModel) {
            state.onEach { }.launchIn(this@MusicFragment.lifecycleScope)
            noticeList.onEach { noticeRVAdapter.submitList(it) }
                .launchIn(this@MusicFragment.lifecycleScope)
            musicChart.onEach { musicChartRVAdapter.submitList(it) }
                .launchIn(this@MusicFragment.lifecycleScope)
            newMusics.onEach { newMusicRVAdapter.submitList(it) }
                .launchIn(this@MusicFragment.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}