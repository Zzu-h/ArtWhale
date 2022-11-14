package com.capstone.artwhale.domain.usecase.notice.impl

import com.capstone.artwhale.domain.model.Notice
import com.capstone.artwhale.domain.repository.NoticeRepository
import com.capstone.artwhale.domain.usecase.notice.GetNoticeUseCase
import javax.inject.Inject

class GetNoticeUseCaseImpl @Inject constructor(
    private val repository: NoticeRepository
) : GetNoticeUseCase {

    override suspend operator fun invoke(): Result<List<Notice>> =
        runCatching { repository.getNotice() }
}