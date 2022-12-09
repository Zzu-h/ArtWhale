package com.capstone.artwhale.domain.usecase.album

import com.capstone.artwhale.domain.model.AiTempImage
import com.capstone.artwhale.domain.model.Mood

interface GetAiAlbumImageListUseCase {

    suspend operator fun invoke(
        title: String,
        lyrics: String,
        mood: Mood? = null
    ): Result<List<AiTempImage>>
}