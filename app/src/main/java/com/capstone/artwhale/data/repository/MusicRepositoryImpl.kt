package com.capstone.artwhale.data.repository

import com.capstone.artwhale.data.datasource.music.MusicDataSource
import com.capstone.artwhale.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicDataSource: MusicDataSource
) : MusicRepository {}