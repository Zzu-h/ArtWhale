package com.capstone.artwhale.presentation.home.profile.my

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentMyArtBinding
import com.capstone.artwhale.presentation.home.profile.common.adapter.ListVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MyArtFragment : Fragment() {

    private var _binding: FragmentMyArtBinding? = null
    private val binding get() = _binding!!

    private val category = listOf("Music", "Album")

    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyArtBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
        initButton()
    }

    private fun initButton() {
        binding.ivBack.setOnClickListener { findNavController().navigate(R.id.action_to_profileFragment) }
    }

    private fun initViewPager() {
        with(binding) {
            vpCategory.adapter = ListVPAdapter(this@MyArtFragment)
            TabLayoutMediator(tlCategory, vpCategory) { tab, pos -> tab.text = category[pos] }
                .attach()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_to_profileFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}