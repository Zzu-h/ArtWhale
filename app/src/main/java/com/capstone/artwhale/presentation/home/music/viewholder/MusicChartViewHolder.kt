package com.capstone.artwhale.presentation.home.music.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.capstone.artwhale.databinding.ItemChartMusicBinding
import com.capstone.artwhale.domain.model.Music

class MusicChartViewHolder(private val binding: ItemChartMusicBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(music: Music) {
        binding.item = music
    }
}