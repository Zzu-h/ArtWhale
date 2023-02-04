package com.capstone.artwhale.presentation.register.music.ai.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.capstone.artwhale.databinding.ItemAiImageBinding
import com.capstone.artwhale.domain.model.AiTempImage
import com.capstone.artwhale.presentation.register.music.ai.viewholder.AiImageViewHolder

class AiImageRVAdapter(private val lifecycleOwner: LifecycleOwner) :
    ListAdapter<AiTempImage, AiImageViewHolder>(diffUtil) {

    private var callback: (url: AiTempImage) -> Unit = {}

    fun setCallBack(callback: (url: AiTempImage) -> Unit) {
        this.callback = callback
    }

    override fun onBindViewHolder(holder: AiImageViewHolder, position: Int) {
        holder.bind(getItem(position), callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AiImageViewHolder {
        return AiImageViewHolder(
            ItemAiImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                .apply { lifecycleOwner = this@AiImageRVAdapter.lifecycleOwner }
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AiTempImage>() {
            override fun areItemsTheSame(oldItem: AiTempImage, newItem: AiTempImage): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: AiTempImage, newItem: AiTempImage): Boolean {
                return oldItem.index == newItem.index
            }
        }
    }
}