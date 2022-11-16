package com.capstone.artwhale.domain.usecase.music.impl

import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.domain.repository.MusicRepository
import com.capstone.artwhale.domain.usecase.music.GetMyMusicListUseCase
import javax.inject.Inject

class GetMyMusicListUseCaseImpl @Inject constructor(
    private val repository: MusicRepository
) : GetMyMusicListUseCase {

    override suspend operator fun invoke(): Result<List<Music>> =
        runCatching { repository.getNewMusics() }
}