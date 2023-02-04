package com.capstone.artwhale.presentation.home.music.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.capstone.artwhale.databinding.ItemNoticeBinding
import com.capstone.artwhale.domain.model.Notice

class NoticeViewHolder(private val binding: ItemNoticeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(notice: Notice) {
        binding.item = notice
    }
}