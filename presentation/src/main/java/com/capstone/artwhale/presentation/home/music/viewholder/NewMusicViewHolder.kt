package com.capstone.artwhale.presentation.home.music.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.capstone.artwhale.databinding.ItemNewMusicBinding
import com.capstone.artwhale.domain.model.Music

class NewMusicViewHolder(private val binding: ItemNewMusicBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(music: Music, callback: (music: Music) -> Unit) {
        binding.item = music
        binding.action = callback
    }
}