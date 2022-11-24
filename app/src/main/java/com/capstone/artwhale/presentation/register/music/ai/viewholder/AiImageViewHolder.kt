package com.capstone.artwhale.presentation.register.music.ai.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.capstone.artwhale.databinding.ItemAiImageBinding

class AiImageViewHolder(private val binding: ItemAiImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(url: String, callback: (url: String) -> Unit) {
        binding.url = url
    }
}