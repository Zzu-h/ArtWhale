package com.capstone.artwhale.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.capstone.artwhale.R
import com.capstone.artwhale.util.PlayerState
import com.capstone.artwhale.util.Playing

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

@BindingAdapter("image")
fun ImageView.setImage(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.img_logo_whale)
            .into(this)
    }
}

@BindingAdapter("state")
fun ImageView.setState(state: Boolean) {
    this.isSelected = state
}

@BindingAdapter("layoutMargin")
fun ImageView.setLayoutMargin(dimen: Float) {
    val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    with(layoutParams) {
        bottomMargin = dimen.toInt()
        topMargin = dimen.toInt()
        leftMargin = dimen.toInt()
        rightMargin = dimen.toInt()
    }
    this.layoutParams = layoutParams
}

@BindingAdapter("playerState")
fun ImageButton.setPlayerState(state: PlayerState) {
    this.isSelected = when (state) {
        is Playing -> false
        else -> true
    }
}

@BindingAdapter("nullableText")
fun TextView.setNullableText(string: String?) {
    string?.apply { text = this }
}

@BindingAdapter("songTime")
fun TextView.setSongTime(time: Long?) {
    text = if (time == null) "4:00"
    else {
        val s = time / 1000
        val m = s / 60

        "$m:${String.format("%02d", s % 60)}"
    }
}

@BindingAdapter("songTime")
fun SeekBar.setSongTime(time: Long?) {
    time?.let { this.progress = it.toInt() }
}

@BindingAdapter("endTime")
fun SeekBar.setMaxTime(endTime: Long) {
    this.max = endTime.toInt()
}