package com.unewexp.adventurizer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Insert
    suspend fun insertActivity(activity: ActivityDbModel)

    @Query("SELECT * FROM activities")
    fun getAllActivities(): Flow<List<ActivityDbModel>>

    @Query("DELETE FROM activities WHERE serverId = :id")
    suspend fun deleteActivity(id: String)

}