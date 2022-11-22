package com.capstone.artwhale.presentation.home.profile.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.artwhale.databinding.FragmentLikeAlbumBinding

class AlbumLikeFragment : Fragment() {

    private var _binding: FragmentLikeAlbumBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLikeAlbumBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}