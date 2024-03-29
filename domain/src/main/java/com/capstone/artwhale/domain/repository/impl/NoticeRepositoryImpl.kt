package com.capstone.artwhale.domain.repository.impl

import com.capstone.artwhale.data.datasource.notice.NoticeDataSource
import com.capstone.artwhale.domain.mapper.toNotice
import com.capstone.artwhale.domain.model.Notice
import com.capstone.artwhale.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val noticeDataSource: NoticeDataSource
) : NoticeRepository {

    override suspend fun getNotice(): List<Notice> =
        noticeDataSource.getNoticeList().map { it.toNotice() }
}