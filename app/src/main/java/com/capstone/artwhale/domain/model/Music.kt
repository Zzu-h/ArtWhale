package com.capstone.artwhale.domain.model

import java.io.Serializable

data class Music(
    val albumImgUrl: String,
    val title: String,
    val singer: String,
    val time: Long? = null,
    var isLike: Boolean = false,
) : Serializable