package com.capstone.artwhale.domain.mapper

import com.capstone.artwhale.data.BuildConfig.BASE_URL
import com.capstone.artwhale.data.dto.NoticeDto
import com.capstone.artwhale.domain.model.Notice

fun NoticeDto.toNotice(): Notice = Notice(
    topText = title,
    contentText = content,
    imageUrl = if (fileId == null) null else BASE_URL + fileId?.path
)