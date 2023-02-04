package com.capstone.artwhale.domain.usecase.album.impl

import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.domain.repository.AlbumRepository
import com.capstone.artwhale.domain.usecase.album.GetAlbumRankingUseCase
import javax.inject.Inject

class GetAlbumRankingUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
) : GetAlbumRankingUseCase {
    override suspend fun invoke(): Result<List<Album>> =
        runCatching { albumRepository.getAlbumRanking() }
}