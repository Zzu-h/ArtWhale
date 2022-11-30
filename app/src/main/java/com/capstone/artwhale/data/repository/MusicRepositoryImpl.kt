package com.capstone.artwhale.data.repository

import com.capstone.artwhale.data.datasource.music.MusicDataSource
import com.capstone.artwhale.domain.model.Music
import com.capstone.artwhale.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicDataSource: MusicDataSource
) : MusicRepository {

    override suspend fun getMusicChart(): List<Music> {
        return listOf(
            Music(
                "https://lh3.googleusercontent.com/mJl1ApC-4mnQGVml2yXhDnNntFj-lxWtka7-N5o_Ioa-VDHD5WZFC_2g7cdzOgOuZNLH_1QNMzRSbLTB=w544-h544-l90-rj",
                "dream",
                "케이시",
                3 * 60 * 1000,
                false
            ),
            Music(
                "https://lh3.googleusercontent.com/77GRI8SOJ6K6HyiGcRkRrj1rGO_ESNfpUm1Nk8phTDAI2KevWd8yQaEwZ2PkDyOaSSI5HcPg9HTlVgHWbw=w544-h544-l90-rj",
                "Medicine",
                "New Hope Club",
                3 * 60 * 1000,
                true
            ),
            Music(
                "https://lh3.googleusercontent.com/Zb68_2VA8BTzSHyxDIgQu8hJTZri8PrEGQPORYhG90UIkR02wgVMJGdBvw8Tz_x-Kl7-qWN7FyOuhtw0=w544-h544-l90-rj",
                "Bonfire",
                "Peder Elias",
                3 * 60 * 1000,
                false
            ),
            Music(
                "https://lh3.googleusercontent.com/FZgtqH0qtrPtk0Vn-fQqOfPUEanelSuDfPUaiR3Z7Jb_VNKYgvLQQBtExAyqUNV78UhO20SHYG3nBzAc=w544-h544-l90-rj",
                "하루하루",
                "Bigbang",
                3 * 60 * 1000,
                true
            ),
            Music(
                "https://lh3.googleusercontent.com/Eve_VljkGvgC-jwS30uaa5A4suBNQZD04mQGwmzTsPdHrAfHPntv4tKk2-aN5ltLX4uU8E7Esce7PTM=w544-h544-l90-rj",
                "Talk",
                "Why don't we",
                3 * 60 * 1000,
                false
            ),
        )
    }

    override suspend fun getNewMusics(): List<Music> {
        return listOf(
            Music(
                "https://lh3.googleusercontent.com/mJl1ApC-4mnQGVml2yXhDnNntFj-lxWtka7-N5o_Ioa-VDHD5WZFC_2g7cdzOgOuZNLH_1QNMzRSbLTB=w544-h544-l90-rj",
                "dream",
                "케이시",
                3 * 60 * 1000,
                false
            ),
            Music(
                "https://lh3.googleusercontent.com/77GRI8SOJ6K6HyiGcRkRrj1rGO_ESNfpUm1Nk8phTDAI2KevWd8yQaEwZ2PkDyOaSSI5HcPg9HTlVgHWbw=w544-h544-l90-rj",
                "Medicine",
                "New Hope Club",
                3 * 60 * 1000,
                true
            ),
            Music(
                "https://lh3.googleusercontent.com/Zb68_2VA8BTzSHyxDIgQu8hJTZri8PrEGQPORYhG90UIkR02wgVMJGdBvw8Tz_x-Kl7-qWN7FyOuhtw0=w544-h544-l90-rj",
                "Bonfire",
                "Peder Elias",
                3 * 60 * 1000,
                false
            ),
            Music(
                "https://lh3.googleusercontent.com/FZgtqH0qtrPtk0Vn-fQqOfPUEanelSuDfPUaiR3Z7Jb_VNKYgvLQQBtExAyqUNV78UhO20SHYG3nBzAc=w544-h544-l90-rj",
                "하루하루",
                "Bigbang",
                3 * 60 * 1000,
                true
            ),
            Music(
                "https://lh3.googleusercontent.com/Eve_VljkGvgC-jwS30uaa5A4suBNQZD04mQGwmzTsPdHrAfHPntv4tKk2-aN5ltLX4uU8E7Esce7PTM=w544-h544-l90-rj",
                "Talk",
                "Why don't we",
                3 * 60 * 1000,
                false
            ),
        )
    }

    override suspend fun getMyMusicList(): List<Music> =
        musicDataSource.getMyMusicList()

    override suspend fun getLikeMusicList(): List<Music> =
        musicDataSource.getLikeMusicList()
}