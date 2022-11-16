package com.capstone.artwhale.domain.model

data class UserInfo(
    var email: String,
    var name: String,
    var profileImgUrl: String? = null,
)