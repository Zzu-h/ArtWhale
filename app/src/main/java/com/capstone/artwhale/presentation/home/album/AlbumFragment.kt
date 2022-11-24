package com.capstone.artwhale.presentation.home.album

import android.content.Intent
import android.os.Build
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentAlbumBinding
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.presentation.common.BaseFragment
import com.capstone.artwhale.presentation.home.album.adapter.AlbumRVAdapter
import com.capstone.artwhale.presentation.home.album.adapter.AlbumRankingThumbnailRVAdapter
import com.capstone.artwhale.presentation.register.album.AlbumRegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AlbumFragment : BaseFragment<FragmentAlbumBinding>(FragmentAlbumBinding::inflate) {

    private val viewModel by viewModels<AlbumViewModel>()

    private lateinit var albumRankingThumbnailRVAdapter: AlbumRankingThumbnailRVAdapter
    private lateinit var albumRVAdapter: AlbumRVAdapter

    private var albumRankingList = emptyList<Album>()

    private val autoScrollDelayTime = 3000L
    private var job: Job? = null

    override fun initAfterBinding() {
        binding.viewModel = viewModel
        initRecyclerView()
        initObserver()
    }

    private fun initObserver() {
        with(viewModel) {
            state.onEach { }.launchIn(this@AlbumFragment.lifecycleScope)
            albumRanking.onEach {
                albumRankingList = it
                if (it.isNotEmpty()) setRankingText(0)
                albumRankingThumbnailRVAdapter.submitList(it)
            }
                .launchIn(this@AlbumFragment.lifecycleScope)
            showAlbum.onEach { albumRVAdapter.submitList(it) }
                .launchIn(this@AlbumFragment.lifecycleScope)
            clickListener.onEach {
                if (it == null) return@onEach
                val intent = Intent(requireContext(), AlbumRegisterActivity::class.java)
                when (it) {
                    R.id.iv_default -> startActivity(intent)
                }
            }.launchIn(this@AlbumFragment.lifecycleScope)
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
                        rvAlbumThumbnail.layoutManager!!, 0, 0
                    )
                    val nextIdx = (currentIdx + 1) % size

                    setRankingText(nextIdx)
                    rvAlbumThumbnail.smoothScrollToPosition(nextIdx)
                }
            }
        }
    }

    private fun setRankingText(idx: Int) {
        with(binding) {
            val size = albumRankingList.size
            val nextIdx = (idx + 1) % size

            tsRankingNumber.setCurrentText("${idx + 1}")
            tsRankingTitle.setCurrentText(albumRankingList[idx].title)
            tsRankingMood.setCurrentText(albumRankingList[idx].mood)
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requireActivity().window.statusBarColor = resources.getColor(R.color.white, null)
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requireActivity().window.statusBarColor =
                resources.getColor(android.R.color.transparent, null)
        viewModel.onClickButton(null)
    }

    override fun onDestroyView() {
        job?.cancel()
        super.onDestroyView()
    }
}