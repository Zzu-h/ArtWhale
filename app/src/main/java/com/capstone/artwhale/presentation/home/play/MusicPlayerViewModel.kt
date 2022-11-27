package com.capstone.artwhale.presentation.home.play

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.util.MusicPlayer
import com.capstone.artwhale.util.Playing
import com.capstone.artwhale.util.Preparing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    val musicPlayer: MusicPlayer
) : ViewModel() {
    lateinit var music: Music

    fun updateMusic() {
        viewModelScope.launch(Dispatchers.IO) {
            musicPlayer.updateMusic(music)
        }
    }

    fun onClickPlay() {
        viewModelScope.launch {
            val state = musicPlayer.playerState.value ?: return@launch
            when (state) {
                is Preparing -> Log.e("Tester", "onClickPlay: Loading")
                is Playing -> musicPlayer.pauseMusic()
                else -> musicPlayer.playMusic()
            }
        }
    }
}