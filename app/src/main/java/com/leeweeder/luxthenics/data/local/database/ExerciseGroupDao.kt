package com.leeweeder.luxthenics.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.leeweeder.luxthenics.domain.model.ExerciseGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseGroupDao {
    @Query("SELECT * FROM exercisegroup WHERE id = :id")
    fun getExerciseGroupById(id: Int): Flow<List<ExerciseGroup>>

    @Insert
    suspend fun insertExerciseGroup(exerciseGroup: ExerciseGroup)

    @Update
    suspend fun updateExerciseGroup(exerciseGroup: ExerciseGroup)

    @Delete
    suspend fun deleteExerciseGroup(exerciseGroup: ExerciseGroup)
}