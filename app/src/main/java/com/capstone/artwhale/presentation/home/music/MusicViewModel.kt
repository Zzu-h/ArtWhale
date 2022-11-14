package com.capstone.artwhale.presentation.home.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.domain.model.Notice
import com.capstone.artwhale.domain.usecase.music.GetMusicChartUseCase
import com.capstone.artwhale.domain.usecase.music.GetNewMusicUseCase
import com.capstone.artwhale.domain.usecase.notice.GetNoticeUseCase
import com.capstone.artwhale.presentation.common.Error
import com.capstone.artwhale.presentation.common.InitialState
import com.capstone.artwhale.presentation.common.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getNoticeUseCase: GetNoticeUseCase,
    private val getMusicChartUseCase: GetMusicChartUseCase,
    private val getNewMusicUseCase: GetNewMusicUseCase
) : ViewModel() {

    private val _noticeList = MutableStateFlow<List<Notice>>(emptyList())
    private val _musicChart = MutableStateFlow<List<Music>>(emptyList())
    private val _newMusics = MutableStateFlow<List<Music>>(emptyList())
    private val _state = MutableStateFlow<NetworkState>(InitialState)

    val noticeList: StateFlow<List<Notice>> = _noticeList
    val musicChart: StateFlow<List<Music>> = _musicChart
    val newMusics: StateFlow<List<Music>> = _newMusics
    val state: StateFlow<NetworkState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getNoticeUseCase().onSuccess { _noticeList.emit(it) }
                .onFailure { _state.emit(Error(it.message)) }
            getMusicChartUseCase().onSuccess { _musicChart.emit(it) }
                .onFailure { _state.emit(Error(it.message)) }
            getNewMusicUseCase().onSuccess { _newMusics.emit(it) }
                .onFailure { _state.emit(Error(it.message)) }
        }
    }
}