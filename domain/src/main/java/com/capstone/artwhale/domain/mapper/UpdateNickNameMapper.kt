package com.capstone.artwhale.domain.mapper

import com.capstone.artwhale.data.dto.UpdateNickNameDto

fun String.toUpdateNicknameDto() = UpdateNickNameDto(this)