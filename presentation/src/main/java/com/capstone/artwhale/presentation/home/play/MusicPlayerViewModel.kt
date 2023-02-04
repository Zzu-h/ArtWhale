package com.capstone.artwhale.presentation.home.play

import android.util.Log
import android.widget.SeekBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.domain.usecase.music.UpdateLikeMusicUseCase
import com.capstone.artwhale.util.MusicPlayer
import com.capstone.artwhale.util.PlayerState
import com.capstone.artwhale.util.Playing
import com.capstone.artwhale.util.Preparing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    private val updateLikeMusicUseCase: UpdateLikeMusicUseCase,
    val musicPlayer: MusicPlayer
) : ViewModel() {

    private val _music = MutableStateFlow<Music?>(null)
    val music: StateFlow<Music?> = _music

    private var state: PlayerState = Preparing
    private val moveTime = 5000L

    val sbListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) = Unit

        override fun onStartTrackingTouch(p0: SeekBar?) {
            state = musicPlayer.playerState.value!!
            if (state !is Preparing) musicPlayer.pauseMusic()
        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
            p0?.apply {
                val gap = progress - musicPlayer.mills.value!!
                if (state !is Preparing) musicPlayer.moveTime(gap)
                when (state) {
                    is Preparing -> Log.e("Tester", "Loading")
                    is Playing -> musicPlayer.playMusic()
                    else -> musicPlayer.pauseMusic()
                }
            }
        }
    }

    fun updateMusic(music: Music) {
        viewModelScope.launch(Dispatchers.IO) {
            _music.value = music
            musicPlayer.updateMusic(music)
        }
    }

    fun onClickPlay() {
        viewModelScope.launch {
            state = musicPlayer.playerState.value ?: return@launch
            when (state) {
                is Preparing -> Log.e("Tester", "onClickPlay: Loading")
                is Playing -> musicPlayer.pauseMusic()
                else -> musicPlayer.playMusic()
            }
        }
    }

    fun onClickPrev() {
        viewModelScope.launch { musicPlayer.moveTime(-1 * moveTime) }
    }

    fun onClickNext() {
        viewModelScope.launch { musicPlayer.moveTime(moveTime) }
    }

    fun onClickLike() {
        viewModelScope.launch {
            val music = music.value ?: return@launch
            val isLike = music.isLike
            _music.value = music.copy(isLike = !isLike)
            updateLikeMusicUseCase(music.id)
        }
    }
}