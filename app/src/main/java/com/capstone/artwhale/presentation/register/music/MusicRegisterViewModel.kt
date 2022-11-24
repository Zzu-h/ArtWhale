package com.capstone.artwhale.presentation.register.music

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.domain.model.Mood
import com.capstone.artwhale.domain.usecase.mood.GetMoodListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicRegisterViewModel @Inject constructor(
    private val getMoodListUseCase: GetMoodListUseCase
) : ViewModel() {

    /*초기 설정 변수*/
    private val _isAI = MutableStateFlow(true)
    private val _album = MutableStateFlow<Album?>(null)
    val isAI: StateFlow<Boolean> get() = _isAI
    val album: StateFlow<Album?> get() = _album

    private val _imageUri = MutableStateFlow<String?>(null)
    private val _moodList = MutableStateFlow<List<Mood>>(emptyList())
    private val _selectedMood = MutableStateFlow<Mood?>(null)

    val imageUri: StateFlow<String?> = _imageUri
    val moodList: StateFlow<List<Mood>> = _moodList
    val selectedMood: StateFlow<Mood?> = _selectedMood
    val title = MutableStateFlow("")
    val content = MutableStateFlow("")

    init {
        viewModelScope.launch {
            getMoodListUseCase().onSuccess { _moodList.emit(it) }
        }
    }

    fun setAlbumImage(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) { _imageUri.emit(uri.toString()) }
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
}