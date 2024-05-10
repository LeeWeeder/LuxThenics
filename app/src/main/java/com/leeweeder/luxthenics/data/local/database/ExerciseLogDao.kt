package com.leeweeder.luxthenics.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.leeweeder.luxthenics.domain.model.ExerciseLog
import com.leeweeder.luxthenics.domain.model.Routine
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseLogDao {
    @Query("SELECT * FROM exerciselog")
    fun getExerciseLogs(): Flow<List<ExerciseLog>>

    @Query("SELECT * FROM exerciselog WHERE routineExerciseId IN (SELECT routineId FROM routineexercise WHERE exerciseId = :exerciseId )")
    fun getExerciseLogsByExerciseId(exerciseId: Int): ExerciseLog?

    @Query("SELECT * FROM exerciselog WHERE routineExerciseId IN (SELECT routineId FROM routineexercise WHERE routineId = :routineId )")
    fun getAllExerciseLogByRoutineId(routineId: Int): ExerciseLog?

    @Insert
    suspend fun insertExerciseLog(exerciseLog: ExerciseLog)

    @Update
    suspend fun updateExerciseLog(exerciseLog: ExerciseLog)

    @Delete
    suspend fun deleteExerciseLog(exerciseLog: ExerciseLog)
}