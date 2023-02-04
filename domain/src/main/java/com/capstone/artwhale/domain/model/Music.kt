package com.capstone.artwhale.domain.model

import java.io.Serializable

data class Music(
    val id: Int = 0,
    val albumImgUrl: String,
    val title: String,
    val singer: String,
    val time: Long? = null,
    var isLike: Boolean = false,
    val mood: String = "Happy",
    val musicUrl: String = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3"
) : Serializable