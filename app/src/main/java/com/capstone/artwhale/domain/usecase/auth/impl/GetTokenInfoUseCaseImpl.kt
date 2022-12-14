package com.capstone.artwhale.domain.usecase.auth.impl

import com.capstone.artwhale.domain.model.TokenInfo
import com.capstone.artwhale.domain.repository.AuthRepository
import com.capstone.artwhale.domain.usecase.auth.GetTokenInfoUseCase
import javax.inject.Inject

class GetTokenInfoUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetTokenInfoUseCase {

    override suspend fun invoke(): Result<TokenInfo> = authRepository.getTokenInfo()
}