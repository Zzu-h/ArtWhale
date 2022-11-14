package com.capstone.artwhale.presentation.home.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.domain.usecase.album.GetAlbumRankingUseCase
import com.capstone.artwhale.domain.usecase.album.GetAllAlbumUseCase
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
class AlbumViewModel @Inject constructor(
    private val getAllAlbumUseCase: GetAllAlbumUseCase,
    private val getAlbumRankingUseCase: GetAlbumRankingUseCase
) : ViewModel() {

    private val _albumRanking = MutableStateFlow<List<Album>>(emptyList())
    private val _allAlbum = MutableStateFlow<List<Album>>(emptyList())
    private val _state = MutableStateFlow<NetworkState>(InitialState)

    val albumRanking: StateFlow<List<Album>> = _albumRanking
    val allAlbum: StateFlow<List<Album>> = _allAlbum
    val state: StateFlow<NetworkState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllAlbumUseCase().onSuccess { _albumRanking.emit(it) }
                .onFailure { _state.emit(Error(it.message)) }
            getAlbumRankingUseCase().onSuccess { _allAlbum.emit(it) }
                .onFailure { _state.emit(Error(it.message)) }
        }
    }
}