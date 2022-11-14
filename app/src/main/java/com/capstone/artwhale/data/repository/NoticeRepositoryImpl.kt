package com.capstone.artwhale.data.repository

import com.capstone.artwhale.data.datasource.notice.NoticeDataSource
import com.capstone.artwhale.domain.model.Notice
import com.capstone.artwhale.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val noticeDataSource: NoticeDataSource
) : NoticeRepository {

    override fun getNotice(): List<Notice> {
        return listOf(
            Notice(contentText = "Test1", topText = "Notice", null),
            Notice(
                contentText = null,
                topText = null,
                "https://cdnimg.melon.co.kr/resource/image/cds/musicstory/imgUrl20221104043848193.jpg"
            ),
            Notice(
                contentText = null,
                topText = "Advertisement",
                "https://cdnimg.melon.co.kr/resource/image/cds/musicstory/imgUrl20221108055055744.jpg"
            ),
        )
    }
}