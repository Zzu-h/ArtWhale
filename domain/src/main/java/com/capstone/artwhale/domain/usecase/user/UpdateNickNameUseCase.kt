package com.capstone.artwhale.domain.usecase.user

interface UpdateNickNameUseCase {

    suspend operator fun invoke(nickName: String): Result<Unit>
}