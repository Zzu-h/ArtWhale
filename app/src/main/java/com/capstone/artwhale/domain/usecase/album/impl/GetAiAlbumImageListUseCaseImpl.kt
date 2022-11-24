package com.capstone.artwhale.domain.usecase.album.impl

import com.capstone.artwhale.domain.model.Mood
import com.capstone.artwhale.domain.repository.AlbumRepository
import com.capstone.artwhale.domain.usecase.album.GetAiAlbumImageListUseCase
import javax.inject.Inject

class GetAiAlbumImageListUseCaseImpl @Inject constructor(
    private val albumRepository: AlbumRepository
) : GetAiAlbumImageListUseCase {

    override suspend operator fun invoke(
        title: String,
        lyrics: String,
        mood: Mood?
    ): Result<List<String>> = runCatching { albumRepository.getAiAlbumImageList() }
}