package com.capstone.artwhale.presentation.register.music.ai.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.capstone.artwhale.databinding.ItemAiImageBinding
import com.capstone.artwhale.domain.model.AiTempImage

class AiImageViewHolder(private val binding: ItemAiImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(url: AiTempImage, callback: (url: AiTempImage) -> Unit) {
        binding.aiTempImage = url
        binding.action = callback
    }
}