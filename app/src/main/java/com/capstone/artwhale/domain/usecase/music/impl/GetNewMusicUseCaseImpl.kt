package com.capstone.artwhale.domain.usecase.music.impl

import com.capstone.artwhale.domain.repository.MusicRepository
import com.capstone.artwhale.domain.usecase.music.GetNewMusicUseCase
import javax.inject.Inject

class GetNewMusicUseCaseImpl @Inject constructor(
    private val repository: MusicRepository
) : GetNewMusicUseCase {}