package com.capstone.artwhale.domain.mapper

import com.capstone.artwhale.data.BuildConfig.BASE_URL
import com.capstone.artwhale.data.dto.UserDto
import com.capstone.artwhale.domain.model.UserInfo

fun UserDto.toUserInfo(): UserInfo = UserInfo(
    email = email,
    name = nickname,
    profileImgUrl = if (fileId == null) null else BASE_URL + fileId?.path
)