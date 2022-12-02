package com.capstone.artwhale.presentation.register.album

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.artwhale.domain.model.Mood
import com.capstone.artwhale.domain.usecase.album.RegisterAlbumUseCase
import com.capstone.artwhale.domain.usecase.mood.GetMoodListUseCase
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
class AlbumRegisterViewModel @Inject constructor(
    private val registerAlbumUseCase: RegisterAlbumUseCase,
    private val getMoodListUseCase: GetMoodListUseCase
) : ViewModel() {

    private val _imageUri = MutableStateFlow<String?>(null)
    private val _moodList = MutableStateFlow<List<Mood>>(emptyList())
    private val _selectedMood = MutableStateFlow<Mood?>(null)
    private val _state = MutableStateFlow<NetworkState>(InitialState)
    val state: StateFlow<NetworkState> = _state

    val imageUri: StateFlow<String?> = _imageUri
    val moodList: StateFlow<List<Mood>> = _moodList
    val selectedMood: StateFlow<Mood?> = _selectedMood
    val title = MutableStateFlow("")

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

    fun onClickRegister() {
        viewModelScope.launch {
            val error = validCheck()
            if (error != null) {
                _state.emit(Error(error))
                return@launch
            }
            registerAlbumUseCase(imageUri.value!!, title.value, selectedMood.value!!.name)
                .onSuccess { _state.emit(Success) }
                .onFailure { _state.emit(Error(it.message)) }
        }
    }

    private fun validCheck(): String? {
        if (imageUri.value == null) return "이미지를 등록해 주세요!"
        else if (title.value.isBlank()) return "제목을 입력해 주세요!"
        else if (selectedMood.value == null) return "감정을 선택해 주세요!"
        return null
    }
}