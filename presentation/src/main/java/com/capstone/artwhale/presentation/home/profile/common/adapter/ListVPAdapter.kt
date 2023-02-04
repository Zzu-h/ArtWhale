package com.capstone.artwhale.presentation.home.profile.common.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.artwhale.presentation.home.profile.common.AlbumListFragment
import com.capstone.artwhale.presentation.home.profile.common.MusicListFragment

class ListVPAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MusicListFragment()
        else -> AlbumListFragment()
    }
}