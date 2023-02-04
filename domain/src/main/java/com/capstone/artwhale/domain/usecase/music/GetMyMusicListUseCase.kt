package com.capstone.artwhale.domain.usecase.music

import com.capstone.artwhale.domain.model.Music

interface GetMyMusicListUseCase {

    suspend operator fun invoke(): Result<List<Music>>
}