package com.capstone.artwhale.presentation.home.profile.my

import android.content.Context
import androidx.activity.OnBackPressedCallback
import com.capstone.artwhale.R
import com.capstone.artwhale.databinding.FragmentMyArtBinding
import com.capstone.artwhale.presentation.home.BaseFragment
import com.capstone.artwhale.presentation.home.profile.common.adapter.ListVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MyArtFragment : BaseFragment<FragmentMyArtBinding>(FragmentMyArtBinding::inflate) {

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
            vpCategory.adapter = ListVPAdapter(this@MyArtFragment)
            TabLayoutMediator(tlCategory, vpCategory) { tab, pos -> tab.text = category[pos] }
                .attach()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                graphNavigate(R.id.action_to_profileFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}