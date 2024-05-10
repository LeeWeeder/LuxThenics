package com.leeweeder.luxthenics.domain.repository

import com.leeweeder.luxthenics.domain.model.ExerciseLog
import kotlinx.coroutines.flow.Flow

interface ExerciseLogRepository {

    fun getExerciseLogs(): Flow<List<ExerciseLog>>

    fun getExerciseLogsByExerciseId(exerciseId: Int): ExerciseLog?

    fun getExerciseLogsByRoutineId(routineId: Int): ExerciseLog?

    suspend fun insertExerciseLog(exerciseLog: ExerciseLog)

    suspend fun updateExerciseLog(exerciseLog: ExerciseLog)

    suspend fun deleteExerciseLog(exerciseLog: ExerciseLog)
}