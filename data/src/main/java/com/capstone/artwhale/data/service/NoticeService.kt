package com.capstone.artwhale.data.service

import com.capstone.artwhale.data.dto.NoticeDto
import retrofit2.http.GET

interface NoticeService {

    @GET("/api/notice")
    suspend fun getNoticeList(): List<NoticeDto>
}