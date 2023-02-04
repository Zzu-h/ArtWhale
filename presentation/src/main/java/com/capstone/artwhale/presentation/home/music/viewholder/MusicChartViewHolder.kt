package com.capstone.artwhale.presentation.home.music.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.capstone.artwhale.databinding.ItemChartMusicBinding
import com.capstone.artwhale.domain.model.Music

class MusicChartViewHolder(private val binding: ItemChartMusicBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(music: Music, callback: (music: Music) -> Unit, likeCallback: (music: Music) -> Unit) {
        with(binding) {
            item = music
            action = callback
            like = {
                val isLike = it.isLike
                item = it.copy(isLike = !isLike)
                likeCallback(it)
            }
        }
    }
}