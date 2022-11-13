package com.capstone.artwhale.data.datasource.notice

import com.capstone.artwhale.data.service.NoticeService
import javax.inject.Inject

class NoticeDataSourceImpl @Inject constructor(
    private val noticeService: NoticeService
) : NoticeDataSource {}