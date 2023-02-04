package com.capstone.artwhale.domain.repository.impl

import com.capstone.artwhale.data.datasource.album.AlbumDataSource
import com.capstone.artwhale.domain.mapper.toAlbum
import com.capstone.artwhale.domain.mapper.toUpdateLikeDto
import com.capstone.artwhale.domain.model.AiTempImage
import com.capstone.artwhale.domain.model.Album
import com.capstone.artwhale.domain.repository.AlbumRepository
import com.capstone.artwhale.util.LocalPathUtil
import java.io.File
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val localPathUtil: LocalPathUtil,
    private val albumDataSource: AlbumDataSource
) : AlbumRepository {

    override suspend fun getAlbumRanking(): List<Album> {
        val list = albumDataSource.getAllAlbumList().map { it.toAlbum() }
        return if (list.size > 5) list.subList(0, 5) else list
    }

    override suspend fun getAllAlbum(): List<Album> =
        albumDataSource.getAllAlbumList().map { it.toAlbum() }

    override suspend fun getMyAlbumList(): List<Album> =
        albumDataSource.getMyAlbumList().map { it.toAlbum() }

    override suspend fun getLikeAlbumList(): List<Album> =
        albumDataSource.getLikeAlbumList().map { it.toAlbum().copy(isLike = true) }

    override suspend fun getAiAlbumImageList(): List<AiTempImage> {
        val list = listOf(
            "https://lh3.googleusercontent.com/mJl1ApC-4mnQGVml2yXhDnNntFj-lxWtka7-N5o_Ioa-VDHD5WZFC_2g7cdzOgOuZNLH_1QNMzRSbLTB=w544-h544-l90-rj",
            "https://lh3.googleusercontent.com/77GRI8SOJ6K6HyiGcRkRrj1rGO_ESNfpUm1Nk8phTDAI2KevWd8yQaEwZ2PkDyOaSSI5HcPg9HTlVgHWbw=w544-h544-l90-rj",
            "https://lh3.googleusercontent.com/Zb68_2VA8BTzSHyxDIgQu8hJTZri8PrEGQPORYhG90UIkR02wgVMJGdBvw8Tz_x-Kl7-qWN7FyOuhtw0=w544-h544-l90-rj",
            "https://lh3.googleusercontent.com/FZgtqH0qtrPtk0Vn-fQqOfPUEanelSuDfPUaiR3Z7Jb_VNKYgvLQQBtExAyqUNV78UhO20SHYG3nBzAc=w544-h544-l90-rj",
            "https://lh3.googleusercontent.com/Eve_VljkGvgC-jwS30uaa5A4suBNQZD04mQGwmzTsPdHrAfHPntv4tKk2-aN5ltLX4uU8E7Esce7PTM=w544-h544-l90-rj",
            "https://lh3.googleusercontent.com/77GRI8SOJ6K6HyiGcRkRrj1rGO_ESNfpUm1Nk8phTDAI2KevWd8yQaEwZ2PkDyOaSSI5HcPg9HTlVgHWbw=w544-h544-l90-rj",
            "https://lh3.googleusercontent.com/Zb68_2VA8BTzSHyxDIgQu8hJTZri8PrEGQPORYhG90UIkR02wgVMJGdBvw8Tz_x-Kl7-qWN7FyOuhtw0=w544-h544-l90-rj",
            "https://lh3.googleusercontent.com/FZgtqH0qtrPtk0Vn-fQqOfPUEanelSuDfPUaiR3Z7Jb_VNKYgvLQQBtExAyqUNV78UhO20SHYG3nBzAc=w544-h544-l90-rj",
            "https://lh3.googleusercontent.com/Eve_VljkGvgC-jwS30uaa5A4suBNQZD04mQGwmzTsPdHrAfHPntv4tKk2-aN5ltLX4uU8E7Esce7PTM=w544-h544-l90-rj",
        )
        return list.mapIndexed { index, item -> AiTempImage(index, item) }
    }

    override suspend fun updateLikeAlbum(id: Int) =
        albumDataSource.updateLikeAlbum(id.toUpdateLikeDto())

    override suspend fun registerAlbum(uri: String, title: String, mood: String) {
        localPathUtil.getRealPathFromUriString(uri)
            ?.let { albumDataSource.registerAlbum("image", File(it), title, mood) }
    }
}