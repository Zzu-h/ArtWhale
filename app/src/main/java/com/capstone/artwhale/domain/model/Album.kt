package com.capstone.artwhale.domain.model

import java.io.Serializable

data class Album(
    val albumImgUrl: String,
    val title: String,
    val mood: String,
    var isLike: Boolean = false,
) : Serializable