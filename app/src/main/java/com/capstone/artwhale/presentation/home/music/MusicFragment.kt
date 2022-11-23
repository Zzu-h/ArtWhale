package com.capstone.artwhale.presentation.home.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentMusicBinding
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.presentation.home.music.adapter.MusicChartRVAdapter
import com.capstone.artwhale.presentation.home.music.adapter.NewMusicRVAdapter
import com.capstone.artwhale.presentation.home.music.adapter.NoticeRVAdapter
import com.capstone.artwhale.presentation.home.play.MusicPlayerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
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

    private val autoScrollDelayTime = 3000L
    private var job: Job? = null

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

    fun playMusic(music: Music) {
        val player = MusicPlayerFragment()
        val bundle = Bundle()
        bundle.putSerializable("music", music)
        player.arguments = bundle
        player.show(childFragmentManager, getString(R.string.fragment_music_player))
    }

    private fun initRecyclerView() {
        noticeRVAdapter = NoticeRVAdapter()
        musicChartRVAdapter = MusicChartRVAdapter()
            .apply { setCallBack { playMusic(it) } }
        newMusicRVAdapter = NewMusicRVAdapter()
            .apply { setCallBack { playMusic(it) } }

        with(binding) {
            rvNotice.adapter = noticeRVAdapter
            rvChart.adapter = musicChartRVAdapter
            rvNewList.adapter = newMusicRVAdapter

            val pagerSnapHelper = PagerSnapHelper()
            pagerSnapHelper.attachToRecyclerView(rvNotice)

            ci2Notice.attachToRecyclerView(rvNotice, pagerSnapHelper)
            noticeRVAdapter.registerAdapterDataObserver(ci2Notice.adapterDataObserver)

            job = CoroutineScope(Dispatchers.Main).launch {
                while (true) {
                    delay(autoScrollDelayTime)
                    val size = rvNotice.adapter!!.itemCount

                    val currentIdx = pagerSnapHelper.findTargetSnapPosition(
                        rvNotice.layoutManager!!,
                        0,
                        0
                    )
                    rvNotice.smoothScrollToPosition((currentIdx + 1) % size)
                }
            }
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
        job?.cancel()
        super.onDestroyView()
        _binding = null
    }
}