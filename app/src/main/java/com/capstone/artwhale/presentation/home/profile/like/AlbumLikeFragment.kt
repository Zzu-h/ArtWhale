package com.capstone.artwhale.presentation.home.profile.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.artwhale.databinding.FragmentLikeAlbumBinding
import com.capstone.artwhale.presentation.home.album.adapter.AlbumRVAdapter

class AlbumLikeFragment : Fragment() {

    private var _binding: FragmentLikeAlbumBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvAdapter: AlbumRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLikeAlbumBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding) {
            rvAdapter = AlbumRVAdapter()
            rvLikeAlbum.adapter = rvAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}