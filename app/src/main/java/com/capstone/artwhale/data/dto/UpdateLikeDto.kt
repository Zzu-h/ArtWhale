package com.capstone.artwhale.data.dto

import com.google.gson.annotations.SerializedName

data class UpdateLikeDto(
    @SerializedName("id")
    val id: Int
)

fun Int.toUpdateLikeDto() = UpdateLikeDto(this)