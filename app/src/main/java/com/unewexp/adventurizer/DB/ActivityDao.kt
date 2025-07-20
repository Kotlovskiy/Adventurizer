package com.unewexp.adventurizer.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Insert
    suspend fun insertActivity(activity: ActivityBdModel)

    @Query("SELECT * FROM activities")
    suspend fun getAllActivities(): Flow<List<ActivityBdModel>>

    @Query("DELETE FROM activities WHERE serverId = :id")
    suspend fun deleteActivity(id: String)

}