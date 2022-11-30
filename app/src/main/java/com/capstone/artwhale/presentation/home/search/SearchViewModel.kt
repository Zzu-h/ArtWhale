package com.capstone.artwhale.presentation.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.domain.usecase.album.GetAllAlbumUseCase
import com.capstone.artwhale.domain.usecase.music.GetMusicChartUseCase
import com.capstone.artwhale.domain.usecase.recent.GetRecentSearchUseCase
import com.capstone.artwhale.domain.usecase.recent.InsertRecentSearchUseCase
import com.capstone.artwhale.presentation.common.Error
import com.capstone.artwhale.presentation.common.InitialState
import com.capstone.artwhale.presentation.common.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllAlbumUseCase: GetAllAlbumUseCase,
    private val getMusicChartUseCase: GetMusicChartUseCase,
    private val getRecentSearchUseCase: GetRecentSearchUseCase,
    private val insertRecentSearchUseCase: InsertRecentSearchUseCase,
) : ViewModel() {

    private var _allAlbum = emptyList<Album>()
    private var _allMusic = emptyList<Music>()
    private val _showAlbum = MutableStateFlow<List<Album>>(emptyList())
    private val _showMusic = MutableStateFlow<List<Music>>(emptyList())
    private val _state = MutableStateFlow<NetworkState>(InitialState)

    val searchKeyword = MutableStateFlow("")
    val recentSearch = flow {
        getRecentSearchUseCase().onSuccess { flow ->
            flow.collect { emit(it.reversed()) }
        }.onFailure { _state.emit(Error(it.message)) }
    }

    val showAlbum: StateFlow<List<Album>> = _showAlbum
    val showMusic: StateFlow<List<Music>> = _showMusic
    val state: StateFlow<NetworkState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllAlbumUseCase().onSuccess { _allAlbum = it }
                .onFailure { _state.emit(Error(it.message)) }
            getMusicChartUseCase().onSuccess { _allMusic = it }
                .onFailure { _state.emit(Error(it.message)) }
        }
    }

    fun onClickSearch() {
        viewModelScope.launch {
            val query = searchKeyword.value
            if (query.isBlank()) {
                _showAlbum.emit(emptyList())
                _showMusic.emit(emptyList())
                return@launch
            }
            launch(Dispatchers.IO) { insertRecentSearchUseCase(query) }
            val albumTempList = mutableListOf<Album>()
            val musicTempList = mutableListOf<Music>()
            _allAlbum.forEach {
                if (
                    it.title.contains(query, ignoreCase = true) ||
                    it.mood.contains(query, ignoreCase = true)
                ) albumTempList.add(it)
            }
            _allMusic.forEach {
                if (
                    it.title.contains(query, ignoreCase = true) ||
                    it.mood.contains(query, ignoreCase = true)
                ) musicTempList.add(it)
            }
            _showAlbum.emit(albumTempList)
            _showMusic.emit(musicTempList)
        }
    }
}