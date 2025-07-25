package com.unewexp.adventurizer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ActivityDbModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun activityDao(): ActivityDao

    companion object{

        @Volatile
        var Instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return Instance ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                Instance = instance
                instance
            }
        }
    }
}