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
    private val _playerState = MutableLiveData<PlayerState>(Pause)
    val playerState: LiveData<PlayerState> = _playerState

    /*time*/
    private var _minute = 0
    private var _mills = 0L
    private val _second = MutableLiveData(0)
    val minute get() = _minute
    val second: LiveData<Int> = _second

    private var _endTime = 0L
    val endTime get() = _endTime

    /*music Info*/
    private val _music = MutableLiveData<Music>()
    val music: LiveData<Music> = _music

    /*media player*/
    private var mediaPlayer: MediaPlayer? = null

    /*timer*/
    private val timer = Timer()
    private val timerTask = object : TimerTask() {
        override fun run() {
            var second = second.value!!
            while (_mills <= endTime) {
                _mills++
                if (_mills % 1000 == 0L) {
                    second++
                    _second.postValue(second)
                }
            }
            pauseMusic()
        }
    }

    fun playMusic() {
        val player = mediaPlayer ?: return
        _playerState.postValue(Playing)
        player.start()
    }

    fun pauseMusic() {
        val player = mediaPlayer ?: return
        _playerState.postValue(Pause)
        player.pause()
    }

    fun stopMusic() {
        val player = mediaPlayer ?: return
        _playerState.postValue(Stop)
        player.stop()
    }

    fun updateMusic(music: Music): Boolean {
        val prevState = playerState.value!!
        _playerState.postValue(Stop)
        _music.postValue(music)
        _endTime = music.time ?: 0
        _mills = 0L
        _minute = 0
        _second.postValue(0)

        /*player update*/
        if (!initializeMediaPlayer()) return false

        when (prevState) {
            Pause -> pauseMusic()
            Stop -> stopMusic()
            else -> playMusic()
        }

        return true
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
        _mills += (second * 1000)
        _mills = min(max(_mills, endTime), 0)
        player.seekTo(_mills.toInt())
    }

    private fun runTimer() {
        timer.schedule(timerTask, 0, 1)
    }

    private fun stopTimer() {
        timer.cancel()
    }

    private fun initializeMediaPlayer(): Boolean {
        val music = this.music.value ?: return false
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(music.musicUrl)
            prepare() // might take long! (for buffering, etc)
        }
        val player = mediaPlayer ?: return false
        _endTime = player.duration.toLong()
        return true
    }

    fun destroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        timer.cancel()
    }
}