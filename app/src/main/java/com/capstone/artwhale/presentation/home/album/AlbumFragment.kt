package com.capstone.artwhale.presentation.home.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.capstone.artwhale.databinding.FragmentAlbumBinding
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.presentation.home.album.adapter.AlbumRVAdapter
import com.capstone.artwhale.presentation.home.album.adapter.AlbumRankingThumbnailRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val viewModel by viewModels<AlbumViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var albumRankingThumbnailRVAdapter: AlbumRankingThumbnailRVAdapter
    private lateinit var albumRVAdapter: AlbumRVAdapter

    private var albumRankingList = emptyList<Album>()

    private val autoScrollDelayTime = 3000L
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserver()
    }

    private fun initObserver() {
        with(viewModel) {
            state.onEach { }.launchIn(this@AlbumFragment.lifecycleScope)
            albumRanking.onEach {
                albumRankingList = it
                if (it.isNotEmpty())
                    with(binding) {
                        tsRankingNumber.setCurrentText("${1}")
                        tsRankingTitle.setCurrentText(albumRankingList[0].title)
                        tsRankingMood.setCurrentText(albumRankingList[0].mood)
                    }
                albumRankingThumbnailRVAdapter.submitList(it)
            }
                .launchIn(this@AlbumFragment.lifecycleScope)
            allAlbum.onEach { albumRVAdapter.submitList(it) }
                .launchIn(this@AlbumFragment.lifecycleScope)
        }
    }

    private fun initRecyclerView() {
        albumRankingThumbnailRVAdapter = AlbumRankingThumbnailRVAdapter()
        albumRVAdapter = AlbumRVAdapter()

        with(binding) {
            rvAlbumThumbnail.adapter = albumRankingThumbnailRVAdapter
            rvAlbumArt.adapter = albumRVAdapter

            val pagerSnapHelper = PagerSnapHelper()
            pagerSnapHelper.attachToRecyclerView(rvAlbumThumbnail)

            ci2Notice.attachToRecyclerView(rvAlbumThumbnail, pagerSnapHelper)
            albumRankingThumbnailRVAdapter.registerAdapterDataObserver(ci2Notice.adapterDataObserver)

            job = CoroutineScope(Dispatchers.Main).launch {
                while (true) {
                    delay(autoScrollDelayTime)
                    val size = rvAlbumThumbnail.adapter!!.itemCount

                    val currentIdx = pagerSnapHelper.findTargetSnapPosition(
                        rvAlbumThumbnail.layoutManager!!,
                        0,
                        0
                    )
                    val nextIdx = (currentIdx + 1) % size

                    tsRankingNumber.setCurrentText("${nextIdx + 1}")
                    tsRankingTitle.setCurrentText(albumRankingList[nextIdx].title)
                    tsRankingMood.setCurrentText(albumRankingList[nextIdx].mood)

                    rvAlbumThumbnail.smoothScrollToPosition(nextIdx)
                }
            }
        }
    }

    override fun onDestroyView() {
        job?.cancel()
        super.onDestroyView()
        _binding = null
    }
}