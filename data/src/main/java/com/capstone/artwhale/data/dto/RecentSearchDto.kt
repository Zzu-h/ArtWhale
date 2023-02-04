package com.capstone.artwhale.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.capstone.artwhale.data.database.ArtWhaleDataBase

@Entity(tableName = ArtWhaleDataBase.recentSearchTable)
data class RecentSearchDto(
    @PrimaryKey val keyword: String,
)