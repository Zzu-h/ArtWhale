package com.capstone.artwhale.presentation.home.profile.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.capstone.artwhale.databinding.FragmentLikeBinding
import com.capstone.artwhale.presentation.home.profile.common.adapter.ListVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

class LikeFragment : Fragment() {

    private var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding!!

    private val category = listOf("Music", "Album")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
        initButton()
    }

    private fun initButton() {
        binding.ivBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun initViewPager() {
        with(binding) {
            vpCategory.adapter = ListVPAdapter(this@LikeFragment)
            TabLayoutMediator(tlCategory, vpCategory) { tab, pos -> tab.text = category[pos] }
                .attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}