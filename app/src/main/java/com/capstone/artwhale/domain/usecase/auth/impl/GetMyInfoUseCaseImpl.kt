package com.capstone.artwhale.domain.usecase.auth.impl

import com.capstone.artwhale.domain.model.Auth
import com.capstone.artwhale.domain.repository.AuthRepository
import com.capstone.artwhale.domain.usecase.auth.GetMyInfoUseCase
import javax.inject.Inject

class GetMyInfoUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetMyInfoUseCase {

    override suspend fun invoke(email: String): Result<Auth> = authRepository.login(email)
}