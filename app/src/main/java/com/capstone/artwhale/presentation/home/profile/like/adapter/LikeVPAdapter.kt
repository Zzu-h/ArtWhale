package com.capstone.artwhale.presentation.home.profile.like.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.artwhale.presentation.home.profile.like.AlbumLikeFragment
import com.capstone.artwhale.presentation.home.profile.like.MusicLikeFragment

class LikeVPAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MusicLikeFragment()
        else -> AlbumLikeFragment()
    }
}