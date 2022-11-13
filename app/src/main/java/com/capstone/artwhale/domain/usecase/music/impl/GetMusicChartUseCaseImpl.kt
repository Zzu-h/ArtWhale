package com.capstone.artwhale.domain.usecase.music.impl

import com.capstone.artwhale.domain.repository.MusicRepository
import com.capstone.artwhale.domain.usecase.music.GetMusicChartUseCase
import javax.inject.Inject

class GetMusicChartUseCaseImpl @Inject constructor(
    private val repository: MusicRepository
) : GetMusicChartUseCase {}