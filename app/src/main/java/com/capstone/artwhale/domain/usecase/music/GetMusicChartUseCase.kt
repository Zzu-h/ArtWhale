package com.capstone.artwhale.domain.usecase.music

import com.capstone.artwhale.domain.model.Music

interface GetMusicChartUseCase {

    suspend operator fun invoke(): Result<List<Music>>
}