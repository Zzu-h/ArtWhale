package com.capstone.artwhale.presentation.home.album.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.capstone.artwhale.databinding.ItemAlbumBinding
import com.capstone.artwhale.domain.model.Album

class AlbumViewHolder(private val binding: ItemAlbumBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(album: Album, callback: (album: Album) -> Unit) {
        binding.item = album
        binding.action = callback
    }
}