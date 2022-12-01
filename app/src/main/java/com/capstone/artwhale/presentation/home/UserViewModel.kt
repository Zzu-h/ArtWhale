package com.capstone.artwhale.presentation.home

import android.net.Uri
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.domain.model.UserInfo
import com.capstone.artwhale.domain.usecase.album.GetLikeAlbumListUseCase
import com.capstone.artwhale.domain.usecase.album.GetMyAlbumListUseCase
import com.capstone.artwhale.domain.usecase.album.UpdateLikeAlbumUseCase
import com.capstone.artwhale.domain.usecase.music.GetLikeMusicListUseCase
import com.capstone.artwhale.domain.usecase.music.GetMyMusicListUseCase
import com.capstone.artwhale.domain.usecase.music.UpdateLikeMusicUseCase
import com.capstone.artwhale.domain.usecase.user.GetMyInfoUseCase
import com.capstone.artwhale.domain.usecase.user.UpdateNickNameUseCase
import com.capstone.artwhale.domain.usecase.user.UpdateProfileImageUseCase
import com.capstone.artwhale.presentation.common.Error
import com.capstone.artwhale.presentation.common.InitialState
import com.capstone.artwhale.presentation.common.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getMyMusicListUseCase: GetMyMusicListUseCase,
    private val getMyAlbumListUseCase: GetMyAlbumListUseCase,
    private val getLikeMusicListUseCase: GetLikeMusicListUseCase,
    private val getLikeAlbumListUseCase: GetLikeAlbumListUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase,
    private val updateProfileImageUseCase: UpdateProfileImageUseCase,
    private val updateNickNameUseCase: UpdateNickNameUseCase,
    private val updateLikeMusicUseCase: UpdateLikeMusicUseCase,
    private val updateLikeAlbumUseCase: UpdateLikeAlbumUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<NetworkState>(InitialState)
    val state: StateFlow<NetworkState> = _state

    private val _clickListener = MutableStateFlow<Int?>(null)
    val clickListener: StateFlow<Int?> = _clickListener

    private val _myInfo = MutableStateFlow(UserInfo("null", "null"))
    private val _myAlbum = MutableStateFlow<List<Album>>(emptyList())
    private val _likeAlbum = MutableStateFlow<List<Album>>(emptyList())
    private val _myMusic = MutableStateFlow<List<Music>>(emptyList())
    private val _likeMusic = MutableStateFlow<List<Music>>(emptyList())
    private var imageChangeFlag = false

    val myInfo: StateFlow<UserInfo> = _myInfo
    val myAlbum: StateFlow<List<Album>> = _myAlbum
    val likeAlbum: StateFlow<List<Album>> = _likeAlbum
    val myMusic: StateFlow<List<Music>> = _myMusic
    val likeMusic: StateFlow<List<Music>> = _likeMusic
    var rvMode: Boolean = false

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                getMyMusicListUseCase().onSuccess { _myMusic.emit(it) }
                    .onFailure { _state.emit(Error(it.message)) }
            }
            launch {
                getMyAlbumListUseCase().onSuccess { _myAlbum.emit(it) }
                    .onFailure { _state.emit(Error(it.message)) }
            }
            launch {
                getLikeMusicListUseCase().onSuccess { _likeMusic.emit(it) }
                    .onFailure { _state.emit(Error(it.message)) }
            }
            launch {
                getLikeAlbumListUseCase().onSuccess { _likeAlbum.emit(it) }
                    .onFailure { _state.emit(Error(it.message)) }
            }
            launch {
                getMyInfoUseCase().onSuccess { _myInfo.emit(it) }
                    .onFailure { _state.emit(Error(it.message)) }
            }
        }
    }

    fun setProfileImage(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = myInfo.value
            _myInfo.emit(UserInfo(data.email, data.name, profileImgUrl = uri.toString()))
            imageChangeFlag = true
        }
    }

    fun onClickButton(view: View?) {
        viewModelScope.launch { _clickListener.emit(view?.id) }
    }

    fun updateProfileInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            if (imageChangeFlag) launch {
                updateProfileImageUseCase(myInfo.value.profileImgUrl!!)
                imageChangeFlag = false
            }
            launch { updateNickNameUseCase(myInfo.value.name) }
        }
    }

    fun updateAlbumLikeState(album: Album) {
        viewModelScope.launch(Dispatchers.IO) { updateLikeAlbumUseCase(album.id) }
    }

    fun updateMusicLikeState(music: Music) {
        viewModelScope.launch(Dispatchers.IO) { updateLikeMusicUseCase(music.id) }
    }
}