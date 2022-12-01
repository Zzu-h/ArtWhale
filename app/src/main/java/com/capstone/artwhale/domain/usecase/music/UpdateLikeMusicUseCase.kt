package com.capstone.artwhale.domain.usecase.music

interface UpdateLikeMusicUseCase {

    suspend operator fun invoke(id: Int): Result<Unit>
}