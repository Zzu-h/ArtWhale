package com.capstone.artwhale.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.capstone.artwhale.data.database.ArtWhaleDataBase
import com.capstone.artwhale.domain.model.RecentSearch

@Entity(tableName = ArtWhaleDataBase.recentSearchTable)
data class RecentSearchDto(
    @PrimaryKey val keyword: String,
) {
    fun toRecentSearch(): RecentSearch = RecentSearch(keyword)
}

fun RecentSearch.toRecentSearchDto() = RecentSearchDto(keyword)