package com.capstone.artwhale.domain.repository

import com.capstone.artwhale.domain.model.Notice

interface NoticeRepository {

    fun getNotice(): List<Notice>
}