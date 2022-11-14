package com.capstone.artwhale.domain.usecase.auth

import com.capstone.artwhale.domain.model.Auth

interface GetMyInfoUseCase {

    suspend operator fun invoke(email: String): Result<Auth>
}