package com.capstone.artwhale.presentation.home.music

import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentMusicBinding
import com.capstone.artwhale.presentation.common.BaseFragment
import com.capstone.artwhale.presentation.home.UserViewModel
import com.capstone.artwhale.presentation.home.music.adapter.MusicChartRVAdapter
import com.capstone.artwhale.presentation.home.music.adapter.NewMusicRVAdapter
import com.capstone.artwhale.presentation.home.music.adapter.NoticeRVAdapter
import com.capstone.artwhale.presentation.register.music.MusicRegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MusicFragment : BaseFragment<FragmentMusicBinding>(FragmentMusicBinding::inflate) {

    private val viewModel by viewModels<MusicViewModel>()
    private val userViewModel by activityViewModels<UserViewModel>()

    private lateinit var noticeRVAdapter: NoticeRVAdapter
    private lateinit var musicChartRVAdapter: MusicChartRVAdapter
    private lateinit var newMusicRVAdapter: NewMusicRVAdapter

    private val autoScrollDelayTime = 3000L
    private var job: Job? = null

    override fun initAfterBinding() {
        initRecyclerView()
        initObserver()
        initToolBar()
    }

    private fun initToolBar() {
        with(binding.ctbMain) {
            setOnClickDefaultIcon {
                startActivity(Intent(requireContext(), MusicRegisterActivity::class.java))
            }
            setOnClickAdditionalIcon {
                graphNavigate(R.id.action_to_SearchFragment)
            }
        }
    }

    private fun initRecyclerView() {
        noticeRVAdapter = NoticeRVAdapter()
        musicChartRVAdapter = MusicChartRVAdapter()
            .apply {
                setCallBack(
                    { playMusic(it) },
                    { userViewModel.updateMusicLikeState(it) })
            }
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

    override fun onResume() {
        super.onResume()
        viewModel.loadMusicList()
    }

    override fun onDestroyView() {
        job?.cancel()
        super.onDestroyView()
    }
}