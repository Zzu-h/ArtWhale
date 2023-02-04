package com.capstone.artwhale.domain.model

import androidx.lifecycle.MutableLiveData

data class AiTempImage(
    val index: Int,
    val url: String,
    val isSelected: MutableLiveData<Boolean> = MutableLiveData(false)
)