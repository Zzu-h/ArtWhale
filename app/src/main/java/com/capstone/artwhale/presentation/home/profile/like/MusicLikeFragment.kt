package com.capstone.artwhale.presentation.home.profile.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.artwhale.databinding.FragmentLikeMusicBinding
import com.capstone.artwhale.presentation.home.music.adapter.MusicChartRVAdapter

class MusicLikeFragment : Fragment() {

    private var _binding: FragmentLikeMusicBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvAdapter: MusicChartRVAdapter

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