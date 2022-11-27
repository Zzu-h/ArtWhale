package com.capstone.artwhale.util

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.artwhale.domain.model.Music
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

@WorkerThread
class MusicPlayer @Inject constructor(
    private val context: Context
) {

    /*player state*/
    private val _playerState = MutableLiveData<PlayerState>(Preparing)
    val playerState: LiveData<PlayerState> = _playerState

    /*time*/
    private val _mills = MutableLiveData(0L)
    val mills: LiveData<Long> = _mills

    private var mEndTime = 0L
    private val _endTime = MutableLiveData(0L)
    val endTime: LiveData<Long> = _endTime

    /*music Info*/
    private val _music = MutableLiveData<Music>()
    val music: LiveData<Music> = _music

    /*media player*/
    private var mediaPlayer: MediaPlayer? = null

    /*timer*/
    private var _timer: Job? = null

    fun playMusic() {
        val player = mediaPlayer ?: return
        _playerState.postValue(Playing)
        runTimer()
        player.start()
    }

    fun pauseMusic() {
        val player = mediaPlayer ?: return
        _playerState.postValue(Pause)
        stopTimer()
        player.pause()
    }

    fun stopMusic() {
        _playerState.postValue(Stop)
        stopTimer()
        mediaPlayer?.apply { if (isPlaying) stop() }
        _mills.postValue(0L)
    }

    fun updateMusic(music: Music): Boolean {
        stopMusic()
        _music.postValue(music)

        /*player update*/
        return !initializeMediaPlayer(music) {
            _endTime.value = music.time
            mEndTime = music.time ?: 3000L
            playMusic()
        }
    }

    fun moveTime(millieSecond: Long) {
        val player = mediaPlayer ?: return
        val millisecond = mills.value!! + millieSecond
        _mills.postValue(min(max(millisecond, mEndTime), 0L))
        player.seekTo(millisecond.toInt())
    }

    private fun runTimer() {
        stopTimer()
        _timer = CoroutineScope(Dispatchers.Default).launch {
            var millisecond = mills.value!!
            val endT = mEndTime
            while (millisecond <= endT) {
                delay(50)
                millisecond += 50
                _mills.postValue(millisecond)
            }
            pauseMusic()
        }
    }

    private fun stopTimer() {
        _timer?.apply { if (isActive) cancel() }
        _timer = null
    }

    private fun initializeMediaPlayer(
        music: Music,
        onPrepared: MediaPlayer.OnPreparedListener
    ): Boolean {
        mediaPlayer?.release()
        mediaPlayer = null

        mediaPlayer = MediaPlayer().apply {
            _playerState.postValue(Preparing)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(music.musicUrl)
            prepare()
            setOnPreparedListener(onPrepared)
        }
        return mediaPlayer != null
    }

    fun destroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        stopTimer()
    }
}