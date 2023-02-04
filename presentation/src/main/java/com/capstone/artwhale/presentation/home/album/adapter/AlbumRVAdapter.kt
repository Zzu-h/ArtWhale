package com.capstone.artwhale.presentation.home.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.capstone.artwhale.databinding.ItemAlbumBinding
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.presentation.home.album.viewholder.AlbumViewHolder

class AlbumRVAdapter :
    ListAdapter<Album, AlbumViewHolder>(diffUtil) {

    private var callback: (album: Album) -> Unit = {}
    private var likeCallback: (album: Album) -> Unit = {}

    fun setCallBack(callback: (album: Album) -> Unit, likeCallback: (music: Album) -> Unit) {
        this.callback = callback
        this.likeCallback = likeCallback
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position), callback, likeCallback)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        return AlbumViewHolder(
            ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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