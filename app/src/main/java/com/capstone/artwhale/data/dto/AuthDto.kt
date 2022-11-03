package com.capstone.artwhale.data.dto

import com.capstone.artwhale.domain.model.Auth

data class AuthDto(val jwt: String) {
    fun toAuth(): Auth = Auth(token = jwt)
}