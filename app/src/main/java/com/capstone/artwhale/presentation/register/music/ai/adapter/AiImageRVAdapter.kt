package com.capstone.artwhale.presentation.register.music.ai.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.capstone.artwhale.databinding.ItemAiImageBinding
import com.capstone.artwhale.presentation.register.music.ai.viewholder.AiImageViewHolder

class AiImageRVAdapter : ListAdapter<String, AiImageViewHolder>(diffUtil) {

    private var callback: (url: String) -> Unit = {}

    fun setCallBack(callback: (url: String) -> Unit) {
        this.callback = callback
    }

    override fun onBindViewHolder(holder: AiImageViewHolder, position: Int) {
        holder.bind(getItem(position), callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AiImageViewHolder {
        return AiImageViewHolder(
            ItemAiImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}