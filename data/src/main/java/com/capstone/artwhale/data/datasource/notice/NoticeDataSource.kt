package com.capstone.artwhale.data.datasource.notice

import com.capstone.artwhale.data.dto.NoticeDto

interface NoticeDataSource {

    suspend fun getNoticeList(): List<NoticeDto>
}