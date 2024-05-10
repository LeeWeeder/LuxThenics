package com.leeweeder.luxthenics.data.local.repository

import com.leeweeder.luxthenics.data.local.database.ExerciseLogDao
import com.leeweeder.luxthenics.domain.model.ExerciseLog
import com.leeweeder.luxthenics.domain.repository.ExerciseLogRepository
import kotlinx.coroutines.flow.Flow

class ExerciseLogRepositoryImpl(
    private val dao: ExerciseLogDao
): ExerciseLogRepository {
    override fun getExerciseLogs(): Flow<List<ExerciseLog>> {
        return dao.getExerciseLogs()
    }

    override fun getExerciseLogsByExerciseId(exerciseId: Int): ExerciseLog? {
        return dao.getExerciseLogsByExerciseId(exerciseId)
    }

    override fun getExerciseLogsByRoutineId(routineId: Int): ExerciseLog? {
        return dao.getAllExerciseLogByRoutineId(routineId)
    }

    override suspend fun insertExerciseLog(exerciseLog: ExerciseLog) {
        dao.insertExerciseLog(exerciseLog)
    }

    override suspend fun updateExerciseLog(exerciseLog: ExerciseLog) {
        dao.updateExerciseLog(exerciseLog)
    }

    override suspend fun deleteExerciseLog(exerciseLog: ExerciseLog) {
        deleteExerciseLog(exerciseLog)
    }
}