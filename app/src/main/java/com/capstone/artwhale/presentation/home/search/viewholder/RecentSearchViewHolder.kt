package com.capstone.artwhale.presentation.home.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.capstone.artwhale.databinding.ItemRecentSearchBinding
import com.capstone.artwhale.presentation.home.search.adapter.RecentSearchRVAdapter

class RecentSearchViewHolder(private val binding: ItemRecentSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String, callback: RecentSearchRVAdapter.ClickListener) {
        binding.item = item
        binding.listener = callback
    }
}