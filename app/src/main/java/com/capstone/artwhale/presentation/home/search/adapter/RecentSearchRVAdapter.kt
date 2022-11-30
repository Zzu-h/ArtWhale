package com.capstone.artwhale.presentation.home.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.capstone.artwhale.databinding.ItemRecentSearchBinding
import com.capstone.artwhale.domain.model.RecentSearch
import com.capstone.artwhale.presentation.home.search.viewholder.RecentSearchViewHolder

class RecentSearchRVAdapter :
    ListAdapter<RecentSearch, RecentSearchViewHolder>(diffUtil) {

    lateinit var listener: ClickListener

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSearchViewHolder {
        return RecentSearchViewHolder(
            ItemRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RecentSearch>() {
            override fun areItemsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
                return oldItem.keyword == newItem.keyword
            }
        }
    }

    interface ClickListener {
        fun onClickItem(item: RecentSearch)
        fun onClickDelete(item: RecentSearch)
    }
}