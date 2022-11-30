package com.capstone.artwhale.presentation.home.search

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.databinding.FragmentSearchBinding
import com.capstone.artwhale.domain.model.RecentSearch
import com.capstone.artwhale.presentation.common.BaseFragment
import com.capstone.artwhale.presentation.home.album.adapter.AlbumRVAdapter
import com.capstone.artwhale.presentation.home.music.adapter.MusicChartRVAdapter
import com.capstone.artwhale.presentation.home.search.adapter.RecentSearchRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var albumRVAdapter: AlbumRVAdapter
    private lateinit var musicChartRVAdapter: MusicChartRVAdapter
    private lateinit var recentSearchRVAdapter: RecentSearchRVAdapter

    override fun initAfterBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        initObserver()
        initEditText()
    }

    private fun initEditText() {
        with(binding) {
            etSearch.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
                if (b) {
                    rvRecentSearchKeyword.isVisible = true
                    viewModel?.onFocusStateChange(true)
                } else {
                    rvRecentSearchKeyword.isGone = true
                    viewModel?.onClickSearch()
                }
            }
        }
    }

    private fun initObserver() {
        with(viewModel) {
            state.onEach { }.launchIn(this@SearchFragment.lifecycleScope)
            showAlbum.onEach { albumRVAdapter.submitList(it) }
                .launchIn(this@SearchFragment.lifecycleScope)
            showMusic.onEach { musicChartRVAdapter.submitList(it) }
                .launchIn(this@SearchFragment.lifecycleScope)
            recentSearch.onEach { recentSearchRVAdapter.submitList(it) }
                .launchIn(this@SearchFragment.lifecycleScope)
            focusState.onEach {
                if (!it) {
                    binding.etSearch.clearFocus()
                    binding.rvRecentSearchKeyword.requestFocus()
                }
            }.launchIn(this@SearchFragment.lifecycleScope)
        }
    }

    private fun initRecyclerView() {
        albumRVAdapter = AlbumRVAdapter().apply { setCallBack { registerMusicWithAlbum(it) } }
        musicChartRVAdapter = MusicChartRVAdapter().apply { setCallBack { playMusic(it) } }
        recentSearchRVAdapter = RecentSearchRVAdapter().apply {
            listener = object : RecentSearchRVAdapter.ClickListener {
                override fun onClickItem(item: RecentSearch) {}
                override fun onClickDelete(item: RecentSearch) {}
            }
        }
        with(binding) {
            rvAlbumArt.adapter = albumRVAdapter
            rvMusicArt.adapter = musicChartRVAdapter
            rvRecentSearchKeyword.adapter = recentSearchRVAdapter
        }
    }
}