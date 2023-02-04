package com.capstone.artwhale.domain.usecase.auth

import com.capstone.artwhale.domain.model.UserInfo

interface LoginUseCase {

    suspend operator fun invoke(email: String): Result<UserInfo>
}