package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.Notice

interface NoticeRepository {

    suspend fun getNotice(): List<Notice>
}