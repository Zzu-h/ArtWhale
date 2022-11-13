package com.capstone.artwhale.presentation.home.music

import androidx.lifecycle.ViewModel
import com.capstone.artwhale.domain.usecase.music.GetMusicChartUseCase
import com.capstone.artwhale.domain.usecase.music.GetNewMusicUseCase
import com.capstone.artwhale.domain.usecase.notice.GetNoticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getNoticeUseCase: GetNoticeUseCase,
    private val getMusicChartUseCase: GetMusicChartUseCase,
    private val getNewMusicUseCase: GetNewMusicUseCase
) : ViewModel() {
}