package com.capstone.artwhale.presentation.register.album

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumRegisterViewModel @Inject constructor() : ViewModel() {

    private val _imageUri = MutableStateFlow<String?>(null)

    val imageUri: StateFlow<String?> = _imageUri
    val title = MutableStateFlow("")

    fun setAlbumImage(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) { _imageUri.emit(uri.toString()) }
    }
}