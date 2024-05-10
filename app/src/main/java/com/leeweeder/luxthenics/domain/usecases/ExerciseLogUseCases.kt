package com.leeweeder.luxthenics.domain.usecases

import com.leeweeder.luxthenics.domain.model.ExerciseLog
import com.leeweeder.luxthenics.domain.repository.ExerciseLogRepository
import kotlinx.coroutines.flow.Flow

class GetExerciseLogs(
    private val repository: ExerciseLogRepository
) {
    operator fun invoke(): Flow<List<ExerciseLog>> {
        return repository.getExerciseLogs()
    }
}

class GetExerciseLogsByExerciseId(
    private val repository: ExerciseLogRepository
) {
    operator fun invoke(exerciseId: Int): ExerciseLog? {
        return repository.getExerciseLogsByExerciseId(exerciseId)
    }
}

class GetExerciseLogsByRoutineId(
    private val repository: ExerciseLogRepository
) {
    operator fun invoke(routineId: Int): ExerciseLog? {
        return repository.getExerciseLogsByRoutineId(routineId)
    }
}

class InsertExerciseLog(
    private val repository: ExerciseLogRepository
) {
    suspend operator fun invoke(exerciseLog: ExerciseLog) {
        return repository.insertExerciseLog(exerciseLog)
    }
}

class UpdateExerciseLog(
    private val repository: ExerciseLogRepository
) {
    suspend operator fun invoke(exerciseLog: ExerciseLog) {
        return repository.updateExerciseLog(exerciseLog)
    }
}

class DeleteExerciseLog(
    private val repository: ExerciseLogRepository
) {
    suspend operator fun invoke(exerciseLog: ExerciseLog) {
        return repository.deleteExerciseLog(exerciseLog)
    }
}

data class ExerciseLogUseCases(
    val getExerciseLogs: GetExerciseLogs,
    val getExerciseLogsByExerciseId: GetExerciseLogsByExerciseId,
    val getExerciseLogByRoutineId: GetExerciseLogsByRoutineId,
    val insertExerciseLog: InsertExerciseLog,
    val updateExerciseLog: UpdateExerciseLog,
    val deleteExerciseLog: DeleteExerciseLog
)