package com.leeweeder.luxthenics.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leeweeder.luxthenics.data.domain.model.Exercise
import com.leeweeder.luxthenics.data.domain.model.ExerciseDao

@Database(entities = [Exercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}
