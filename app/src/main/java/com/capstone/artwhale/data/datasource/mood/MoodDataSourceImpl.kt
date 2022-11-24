package com.capstone.artwhale.data.datasource.mood

import com.capstone.artwhale.data.service.MoodService
import javax.inject.Inject

class MoodDataSourceImpl @Inject constructor(
    private val moodService: MoodService
) : MoodDataSource {}