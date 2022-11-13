package com.capstone.artwhale.domain.usecase.music.impl

import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.domain.repository.MusicRepository
import com.capstone.artwhale.domain.usecase.music.GetMusicChartUseCase
import javax.inject.Inject

class GetMusicChartUseCaseImpl @Inject constructor(
    private val repository: MusicRepository
) : GetMusicChartUseCase {

    override suspend operator fun invoke(): Result<List<Music>> =
        runCatching { repository.getMusicChart() }
}