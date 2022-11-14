package com.capstone.artwhale.presentation.home.album.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.capstone.artwhale.databinding.ItemRvImageBinding
import com.capstone.artwhale.domain.model.Album

class AlbumRankingThumbnailViewHolder(private val binding: ItemRvImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(album: Album) {
        binding.url = album.albumImgUrl
    }
}