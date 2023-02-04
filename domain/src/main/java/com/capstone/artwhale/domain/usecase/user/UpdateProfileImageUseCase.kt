package com.capstone.artwhale.domain.usecase.user

import com.capstone.artwhale.domain.model.UserInfo

interface UpdateProfileImageUseCase {

    suspend operator fun invoke(uri: String): Result<Unit>
}