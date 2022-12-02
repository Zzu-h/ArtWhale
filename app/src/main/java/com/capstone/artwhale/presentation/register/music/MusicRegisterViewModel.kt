package com.capstone.artwhale.presentation.register.music

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.domain.model.Mood
import com.capstone.artwhale.domain.usecase.album.GetAiAlbumImageListUseCase
import com.capstone.artwhale.domain.usecase.mood.GetMoodListUseCase
import com.capstone.artwhale.domain.usecase.music.RegisterMusicUseCase
import com.capstone.artwhale.presentation.common.Error
import com.capstone.artwhale.presentation.common.InitialState
import com.capstone.artwhale.presentation.common.NetworkState
import com.capstone.artwhale.presentation.common.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicRegisterViewModel @Inject constructor(
    private val registerMusicUseCase: RegisterMusicUseCase,
    private val getMoodListUseCase: GetMoodListUseCase,
    private val getAiAlbumImageListUseCase: GetAiAlbumImageListUseCase
) : ViewModel() {

    /*초기 설정 변수*/
    private val _isAI = MutableStateFlow(true)
    private val _album = MutableStateFlow<Album?>(null)
    private val _state = MutableStateFlow<NetworkState>(InitialState)
    val isAI: StateFlow<Boolean> get() = _isAI
    val album: StateFlow<Album?> get() = _album
    val state: StateFlow<NetworkState> = _state

    private val _musicUri = MutableStateFlow<Uri?>(null)
    private val _moodList = MutableStateFlow<List<Mood>>(emptyList())
    private val _selectedMood = MutableStateFlow<Mood?>(null)
    private val _aiAlbumImageList = MutableStateFlow<List<String>>(emptyList())

    val musicUri: StateFlow<Uri?> = _musicUri
    val moodList: StateFlow<List<Mood>> = _moodList
    val selectedMood: StateFlow<Mood?> = _selectedMood
    val title = MutableStateFlow("")
    val content = MutableStateFlow("")
    val aiAlbumImageList: StateFlow<List<String>> = _aiAlbumImageList

    init {
        viewModelScope.launch {
            getMoodListUseCase().onSuccess { _moodList.emit(it) }
        }
    }

    fun clearAiImageList() {
        viewModelScope.launch(Dispatchers.IO) { _aiAlbumImageList.emit(emptyList()) }
    }

    fun setMusic(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) { _musicUri.emit(uri) }
    }

    fun selectMood(moodIdx: Int) {
        viewModelScope.launch(Dispatchers.IO) { _selectedMood.emit(moodList.value[moodIdx]) }
    }

    fun setAlbum(album: Album) {
        viewModelScope.launch {
            _isAI.emit(false)
            _album.emit(album)
        }
    }

    fun getAiAlbumImageList() {
        viewModelScope.launch {
            getAiAlbumImageListUseCase(
                title.value,
                content.value
            ).onSuccess { _aiAlbumImageList.emit(it) }
        }
    }

    fun onClickRegister() {
        viewModelScope.launch {
            val error = validCheck()
            if (error != null) {
                _state.emit(Error(error))
                return@launch
            }
            registerMusicUseCase(
                musicUri.value!!,
                title.value,
                selectedMood.value!!.name,
                content.value,
                album.value!!.id
            )
                .onSuccess { _state.emit(Success) }
                .onFailure { _state.emit(Error(it.message)) }
        }
    }

    private fun validCheck(): String? {
        if (musicUri.value == null) return "음원을 등록해 주세요!"
        else if (title.value.isBlank()) return "제목을 입력해 주세요!"
        else if (selectedMood.value == null) return "감정을 선택해 주세요!"
        else if (content.value.isBlank()) return "가사를 입력해 주세요!"
        else if (!isAI.value && album.value == null) return "앨범이 선택되지 않았습니다!"
        return null
    }
}