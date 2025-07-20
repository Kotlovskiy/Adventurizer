package com.unewexp.adventurizer.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class ActivityDbModel(
    @PrimaryKey
    val serverId: String,

    val title: String,
    val category: String,
    val difficulty: String,
    val price: Float
)