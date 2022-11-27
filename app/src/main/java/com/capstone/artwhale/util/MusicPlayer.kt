package com.capstone.artwhale.util

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.capstone.artwhale.domain.model.Music
import java.util.*
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
    private var _minute = 0
    private val _second = MutableLiveData(0)
    private val _mills = MutableLiveData(0L)
    val minute get() = _minute
    val second: LiveData<Int> = _second
    val mills: LiveData<Long> = _mills

    private var _endTime = 0L
    val endTime get() = _endTime

    /*music Info*/
    private val _music = MutableLiveData<Music>()
    val music: LiveData<Music> = _music

    /*media player*/
    private var mediaPlayer: MediaPlayer? = null

    /*timer*/
    private var timer: Timer? = null
    private val timerTask = object : TimerTask() {
        override fun run() {
            var second = second.value!!
            var millisecond = mills.value!!
            while (millisecond <= endTime) {
                millisecond++
                _mills.value = millisecond
                if (millisecond % 1000 == 0L) {
                    second++
                    _second.value = second
                }
            }
            pauseMusic()
        }
    }

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
        _minute = 0
        _second.postValue(0)
        _mills.postValue(0L)
    }

    fun updateMusic(music: Music): Boolean {
        val prevState = playerState.value!!
        stopMusic()
        _music.postValue(music)

        /*player update*/
        return !initializeMediaPlayer(music) {
            _endTime = it.duration.toLong()
            playMusic()
        }
    }

    fun moveTime(second: Int) {
        val player = mediaPlayer ?: return
        val prevSecond = this.second.value!!
        val gap = prevSecond + second
        if (gap < 0) {
            _minute = min(0, minute - 1)
            this._second.postValue(60 - gap)
        } else if (gap >= 60) {
            _minute++
            this._second.postValue(gap - 60)
        } else {
            this._second.postValue(gap)
        }
        val millisecond = mills.value!! + (second * 1000)
        _mills.postValue(min(max(millisecond, endTime), 0L))
        player.seekTo(millisecond.toInt())
    }

    private fun runTimer() {
        timer = Timer()
        timer?.schedule(timerTask, 0, 1)
    }

    private fun stopTimer() {
        timer?.cancel()
        timer = null
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
        timer?.purge()
    }
}