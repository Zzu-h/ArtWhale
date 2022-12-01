package com.capstone.artwhale.domain.usecase.user

import com.capstone.artwhale.domain.model.UserInfo

interface GetMyInfoUseCase {

    suspend operator fun invoke(): Result<UserInfo>
}