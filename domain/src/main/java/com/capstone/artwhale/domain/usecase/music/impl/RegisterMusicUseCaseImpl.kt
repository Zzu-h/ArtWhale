package com.capstone.artwhale.domain.usecase.music.impl

import android.net.Uri
import com.capstone.artwhale.domain.repository.MusicRepository
import com.capstone.artwhale.domain.usecase.music.RegisterMusicUseCase
import javax.inject.Inject

class RegisterMusicUseCaseImpl @Inject constructor(
    private val musicRepository: MusicRepository
) : RegisterMusicUseCase {

    override suspend operator fun invoke(
        uri: Uri,
        title: String,
        mood: String,
        lyrics: String,
        albumArtId: Int
    ): Result<Unit> =
        runCatching { musicRepository.registerMusic(uri, title, mood, lyrics, albumArtId) }
}