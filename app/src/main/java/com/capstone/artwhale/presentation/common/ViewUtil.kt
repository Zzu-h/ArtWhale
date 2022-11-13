package com.capstone.artwhale.presentation.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.capstone.artwhale.R

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
    this.isEnabled = state
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