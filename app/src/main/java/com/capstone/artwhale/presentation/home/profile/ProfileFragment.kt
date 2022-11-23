package com.capstone.artwhale.presentation.home.profile

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentProfileBinding
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.presentation.home.UserViewModel
import com.capstone.artwhale.presentation.home.album.adapter.AlbumRVAdapter
import com.capstone.artwhale.presentation.home.music.adapter.MusicChartRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var albumRVAdapter: AlbumRVAdapter
    private lateinit var chartRVAdapter: MusicChartRVAdapter

    private val viewModel by activityViewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        initNavigation()
        initBlurView()
        initRecyclerView()
        initObserver()
    }

    private fun initRecyclerView() {
        chartRVAdapter = MusicChartRVAdapter()
        albumRVAdapter = AlbumRVAdapter()
        binding.rvChart.adapter = chartRVAdapter
        binding.rvNewList.adapter = albumRVAdapter
    }

    private fun initObserver() {
        this.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                with(viewModel) {
                    launch {
                        likeAlbum.collect {
                            var count = 0
                            val list = mutableListOf<Album>()
                            it.forEach {
                                if (count < 3) {
                                    list.add(it)
                                    count++
                                }
                            }
                            albumRVAdapter.submitList(list)
                        }
                    }
                    launch {
                        likeMusic.collect {
                            var count = 0
                            val list = mutableListOf<Music>()
                            it.forEach {
                                if (count < 5 && it.isLike) {
                                    list.add(it)
                                    count++
                                }
                            }
                            chartRVAdapter.submitList(list)
                        }
                    }
                    launch {
                        clickListener.collect {
                            if (it == null) return@collect
                            when (it) {
                                R.id.tv_all_music -> findNavController().navigate(R.id.action_to_likeFragment)
                                R.id.tv_all_album -> findNavController().navigate(R.id.action_to_likeFragment)
                                R.id.ll_songs -> findNavController().navigate(R.id.action_to_myArtFragment)
                                R.id.ll_albums -> findNavController().navigate(R.id.action_to_myArtFragment)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initBlurView() {
        Blurry.with(this.requireContext())
            .radius(10)
            .sampling(8)
            .async()
            .color(Color.argb(66, 255, 255, 0))
            .animate(500)
            .capture(binding.clRoot)
            .into(binding.ivBlur)
    }

    private fun initNavigation() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fcv_profile)

        val navController = (navHostFragment as NavHostFragment).navController
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
        super.onDestroyView()
        _binding = null
    }
}