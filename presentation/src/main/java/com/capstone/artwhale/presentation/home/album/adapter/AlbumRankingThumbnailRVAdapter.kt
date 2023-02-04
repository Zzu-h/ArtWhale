package com.capstone.artwhale.presentation.home.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.capstone.artwhale.databinding.ItemRvImageBinding
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.presentation.home.album.viewholder.AlbumRankingThumbnailViewHolder

class AlbumRankingThumbnailRVAdapter :
    ListAdapter<Album, AlbumRankingThumbnailViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: AlbumRankingThumbnailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumRankingThumbnailViewHolder {
        return AlbumRankingThumbnailViewHolder(
            ItemRvImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
                return (oldItem.title == newItem.title && oldItem.mood == newItem.mood && oldItem.albumImgUrl == newItem.albumImgUrl)
            }

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem == newItem
            }
        }
    }
}