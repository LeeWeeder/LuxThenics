package com.leeweeder.luxthenics.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.leeweeder.luxthenics.domain.model.RoutineExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineExerciseDao {
    @Query("SELECT * FROM routineexercise")
    fun getRoutineExercises(): Flow<List<RoutineExercise>>

    @Query("SELECT * FROM routineexercise WHERE id = :id")
    fun getRoutineExerciseById(id: Int): RoutineExercise?

    @Query("SELECT * FROM routineexercise WHERE routineId = :routineId")
    fun getRoutineExercisesByRoutineId(routineId: Int): Flow<List<RoutineExercise>>

    @Insert
    suspend fun insertRoutineExercise(routineExercise: RoutineExercise)

    @Update
    suspend fun updateRoutineExercise(routineExercise: RoutineExercise)

    @Delete
    suspend fun deleteRoutineExercise(routineExercise: RoutineExercise)
}