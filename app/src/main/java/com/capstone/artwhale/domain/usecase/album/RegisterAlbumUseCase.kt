package com.capstone.artwhale.domain.usecase.album

interface RegisterAlbumUseCase {

    suspend operator fun invoke(uri: String, title: String, mood: String): Result<Unit>
}