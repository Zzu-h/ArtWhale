package com.capstone.artwhale.domain.mapper

import com.capstone.artwhale.data.dto.UpdateLikeDto

fun Int.toUpdateLikeDto() = UpdateLikeDto(this)