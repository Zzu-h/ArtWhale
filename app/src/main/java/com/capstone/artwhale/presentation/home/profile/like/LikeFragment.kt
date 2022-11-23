package com.capstone.artwhale.presentation.home.profile.like

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentLikeBinding
import com.capstone.artwhale.presentation.home.BaseFragment
import com.capstone.artwhale.presentation.home.profile.common.adapter.ListVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

class LikeFragment : BaseFragment<FragmentLikeBinding>(FragmentLikeBinding::inflate) {

    private val category = listOf("Music", "Album")

    private lateinit var callback: OnBackPressedCallback

    override fun initAfterBinding() {
        initViewPager()
        initButton()
    }

    private fun initButton() {
        binding.ivBack.setOnClickListener { graphNavigate(R.id.action_to_profileFragment) }
    }

    private fun initViewPager() {
        with(binding) {
            vpCategory.adapter = ListVPAdapter(this@LikeFragment)
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
}