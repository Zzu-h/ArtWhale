package com.capstone.artwhale.domain.usecase.notice

import com.capstone.artwhale.domain.model.Notice

interface GetNoticeUseCase {

    suspend operator fun invoke(): Result<List<Notice>>
}