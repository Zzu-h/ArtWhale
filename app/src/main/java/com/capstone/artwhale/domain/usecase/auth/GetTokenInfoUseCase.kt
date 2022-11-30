package com.capstone.artwhale.domain.usecase.auth

import com.capstone.artwhale.domain.model.TokenInfo

interface GetTokenInfoUseCase {

    suspend operator fun invoke(): Result<TokenInfo>
}