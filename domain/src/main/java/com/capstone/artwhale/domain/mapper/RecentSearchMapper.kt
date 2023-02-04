package com.capstone.artwhale.domain.mapper

import com.capstone.artwhale.data.dto.RecentSearchDto
import com.capstone.artwhale.domain.model.RecentSearch

fun RecentSearchDto.toRecentSearch(): RecentSearch = RecentSearch(keyword)

fun RecentSearch.toRecentSearchDto() = RecentSearchDto(keyword)