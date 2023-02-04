package com.capstone.artwhale.presentation.home.album.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.capstone.artwhale.databinding.ItemAlbumBinding
import com.capstone.artwhale.domain.model.Album

class AlbumViewHolder(private val binding: ItemAlbumBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(album: Album, callback: (album: Album) -> Unit, likeCallback: (album: Album) -> Unit) {
        with(binding) {
            item = album
            action = callback
            like = {
                val isLike = it.isLike
                item = it.copy(isLike = !isLike)
                likeCallback(it)
            }
        }
    }
}