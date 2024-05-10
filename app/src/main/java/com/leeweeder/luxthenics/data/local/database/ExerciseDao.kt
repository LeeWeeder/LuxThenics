package com.leeweeder.luxthenics.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.leeweeder.luxthenics.domain.model.Exercise
import com.leeweeder.luxthenics.domain.model.ExerciseWithTargets
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun getExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE id = :id")
    fun getExerciseById(id: Int): Exercise?

    @Transaction
    @Query("SELECT * FROM exercise")
    fun getExerciseWithTargets(): Flow<List<ExerciseWithTargets>>

    @Insert
    suspend fun insertExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)
}