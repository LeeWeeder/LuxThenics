package com.leeweeder.luxthenics.domain.usecases

import com.leeweeder.luxthenics.domain.model.RoutineExercise
import com.leeweeder.luxthenics.domain.repository.RoutineExerciseRepository
import kotlinx.coroutines.flow.Flow

class GetRoutineExercises(
    private val repository: RoutineExerciseRepository
) {
    operator fun invoke(): Flow<List<RoutineExercise>> {
        return repository.getRoutineExercises()
    }
}

class GetRoutineExerciseById(
    private val repository: RoutineExerciseRepository
) {
    operator fun invoke(id: Int): RoutineExercise? {
        return repository.getRoutineExerciseById(id)
    }
}

class GetRoutineExercisesByRoutineId(
    private val repository: RoutineExerciseRepository
) {
    operator fun invoke(routineId: Int): Flow<List<RoutineExercise>> {
        return repository.getRoutineExercisesByRoutineId(routineId)
    }
}

class InsertRoutineExercise(
    private val repository: RoutineExerciseRepository
) {
    suspend operator fun invoke(routineExercise: RoutineExercise) {
        return repository.insertRoutineExercise(routineExercise)
    }
}

class UpdateRoutineExercise(
    private val repository: RoutineExerciseRepository
) {
    suspend operator fun invoke(routineExercise: RoutineExercise) {
        return repository.updateRoutineExercise(routineExercise)
    }
}

class DeleteRoutineExercise(
    private val repository: RoutineExerciseRepository
) {
    suspend operator fun invoke(routineExercise: RoutineExercise) {
        return repository.deleteRoutineExercise(routineExercise)
    }
}

data class RoutineExerciseUseCases(
    val getRoutineExercises: GetRoutineExercises,
    val getRoutineExerciseById: GetRoutineExerciseById,
    val getRoutineExercisesByRoutineId: GetRoutineExercisesByRoutineId,
    val insertRoutineExercise: InsertRoutineExercise,
    val updateRoutineExercise: UpdateRoutineExercise,
    val deleteRoutineExercise: DeleteRoutineExercise
)