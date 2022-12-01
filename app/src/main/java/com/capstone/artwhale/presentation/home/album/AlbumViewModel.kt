package com.capstone.artwhale.presentation.home.album

import android.view.View
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAllAlbumUseCase: GetAllAlbumUseCase,
    private val getAlbumRankingUseCase: GetAlbumRankingUseCase
) : ViewModel() {

    private val _clickListener = MutableStateFlow<Int?>(null)
    val clickListener: StateFlow<Int?> = _clickListener

    private val _albumRanking = MutableStateFlow<List<Album>>(emptyList())
    private var _allAlbum = emptyList<Album>()
    private val _showAlbum = MutableStateFlow<List<Album>>(emptyList())
    private val _state = MutableStateFlow<NetworkState>(InitialState)

    val searchKeyword = MutableStateFlow("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val searchFlow = searchKeyword
        .debounce(500)
        .mapLatest { query ->
            val tempList = mutableListOf<Album>()
            _allAlbum.forEach {
                if (
                    it.title.contains(query, ignoreCase = true) ||
                    it.mood.contains(query, ignoreCase = true)
                ) tempList.add(it)
            }
            _showAlbum.emit(tempList)
        }

    val albumRanking: StateFlow<List<Album>> = _albumRanking
    val showAlbum: StateFlow<List<Album>> = _showAlbum
    val state: StateFlow<NetworkState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAlbumRankingUseCase().onSuccess { _albumRanking.emit(it) }
                .onFailure { _state.emit(Error(it.message)) }
            getAllAlbumUseCase().onSuccess {
                _allAlbum = it
                _showAlbum.emit(it)
            }.onFailure { _state.emit(Error(it.message)) }
            searchFlow.collect()
        }
    }

    fun onClickButton(view: View?) {
        viewModelScope.launch { _clickListener.emit(view?.id) }
    }
}