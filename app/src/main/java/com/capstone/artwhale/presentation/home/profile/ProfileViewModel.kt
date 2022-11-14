package com.capstone.artwhale.presentation.home.profile

import androidx.lifecycle.ViewModel
import com.capstone.artwhale.domain.usecase.album.GetLikeAlbumListUseCase
import com.capstone.artwhale.domain.usecase.album.GetMyAlbumListUseCase
import com.capstone.artwhale.domain.usecase.auth.GetMyInfoUseCase
import com.capstone.artwhale.domain.usecase.music.GetLikeMusicListUseCase
import com.capstone.artwhale.domain.usecase.music.GetMyMusicListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getMyMusicListUseCase: GetMyMusicListUseCase,
    private val getMyAlbumListUseCase: GetMyAlbumListUseCase,
    private val getLikeMusicListUseCase: GetLikeMusicListUseCase,
    private val getLikeAlbumListUseCase: GetLikeAlbumListUseCase,
    private val getMyInfoUseCase: GetMyInfoUseCase,
) : ViewModel()