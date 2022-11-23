package com.capstone.artwhale.presentation.home.profile.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentLikeMusicBinding
import com.capstone.artwhale.presentation.home.music.adapter.MusicChartRVAdapter
import com.capstone.artwhale.presentation.home.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MusicListFragment : Fragment() {

    private var _binding: FragmentLikeMusicBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvAdapter: MusicChartRVAdapter
    private val viewModel by hiltNavGraphViewModels<UserViewModel>(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLikeMusicBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.likeMusic.onEach {
            rvAdapter.submitList(it)
        }.launchIn(this.lifecycleScope)
    }

    private fun initRecyclerView() {
        with(binding) {
            rvAdapter = MusicChartRVAdapter()
            rvLikeMusic.adapter = rvAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}