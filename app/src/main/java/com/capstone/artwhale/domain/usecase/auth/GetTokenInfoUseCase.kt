package com.capstone.artwhale.domain.usecase.auth

import com.capstone.artwhale.domain.model.UserInfo

interface GetTokenInfoUseCase {

    suspend operator fun invoke(): Result<UserInfo>
}