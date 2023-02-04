package com.capstone.artwhale.domain.usecase.album

interface UpdateLikeAlbumUseCase {

    suspend operator fun invoke(id: Int): Result<Unit>
}