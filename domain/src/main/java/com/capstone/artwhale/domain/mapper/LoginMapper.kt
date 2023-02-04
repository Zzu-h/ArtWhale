package com.capstone.artwhale.domain.mapper

import com.capstone.artwhale.data.dto.LoginDto

fun String.toLoginDto(): LoginDto = LoginDto(this)