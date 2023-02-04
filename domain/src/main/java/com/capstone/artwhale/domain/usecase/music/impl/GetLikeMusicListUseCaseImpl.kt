package com.capstone.artwhale.domain.usecase.music.impl

import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.domain.repository.MusicRepository
import com.capstone.artwhale.domain.usecase.music.GetLikeMusicListUseCase
import javax.inject.Inject

class GetLikeMusicListUseCaseImpl @Inject constructor(
    private val repository: MusicRepository
) : GetLikeMusicListUseCase {

    override suspend operator fun invoke(): Result<List<Music>> =
        runCatching { repository.getLikeMusicList() }
}