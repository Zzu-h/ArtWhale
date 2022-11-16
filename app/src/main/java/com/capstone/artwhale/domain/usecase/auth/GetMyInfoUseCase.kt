package com.capstone.artwhale.domain.usecase.auth

import com.capstone.artwhale.domain.model.UserInfo

interface GetMyInfoUseCase {

    suspend operator fun invoke(): Result<UserInfo>
}