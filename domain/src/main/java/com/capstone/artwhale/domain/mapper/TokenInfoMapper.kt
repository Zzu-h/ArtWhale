package com.capstone.artwhale.domain.mapper

import com.capstone.artwhale.data.dto.TokenInfoDto
import com.capstone.artwhale.domain.model.TokenInfo

fun TokenInfoDto.toTokenInfo(): TokenInfo = TokenInfo(
    email = email,
    nickname = nickname,
    expiresIn = expiresIn
)