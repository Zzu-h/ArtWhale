package com.capstone.artwhale.domain.model

data class TokenInfo(
    val email: String,
    val nickname: String,
    val expiresIn: Long
)