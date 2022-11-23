package com.capstone.artwhale.presentation.home.music.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.capstone.artwhale.databinding.ItemChartMusicBinding
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.presentation.home.music.viewholder.MusicChartViewHolder

class MusicChartRVAdapter :
    ListAdapter<Music, MusicChartViewHolder>(diffUtil) {

    private var callback: (music: Music) -> Unit = {}

    fun setCallBack(callback: (music: Music) -> Unit) {
        this.callback = callback
    }

    override fun onBindViewHolder(holder: MusicChartViewHolder, position: Int) {
        holder.bind(getItem(position), callback = callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicChartViewHolder {
        return MusicChartViewHolder(
            ItemChartMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Music>() {
            override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
                return (oldItem.title == newItem.title && oldItem.singer == newItem.singer && oldItem.albumImgUrl == newItem.albumImgUrl)
            }

            override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
                return oldItem == newItem
            }
        }
    }
}