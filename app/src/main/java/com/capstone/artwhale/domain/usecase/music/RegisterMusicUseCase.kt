package com.capstone.artwhale.domain.usecase.music

import android.net.Uri

interface RegisterMusicUseCase {

    suspend operator fun invoke(
        uri: Uri,
        title: String,
        mood: String,
        lyrics: String,
        albumArtId: Int
    ): Result<Unit>
}