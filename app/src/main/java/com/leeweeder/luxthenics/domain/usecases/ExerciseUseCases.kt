package com.leeweeder.luxthenics.domain.usecases

import com.leeweeder.luxthenics.domain.model.Exercise
import com.leeweeder.luxthenics.domain.model.ExerciseWithTargets
import com.leeweeder.luxthenics.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class GetExercises(
    private val repository: ExerciseRepository
) {
    operator fun invoke(): Flow<List<Exercise>> {
        return repository.getExercises()
    }
}

class GetExerciseById(
    private val repository: ExerciseRepository
) {
    operator fun invoke(id: Int): Exercise? {
        return repository.getExerciseById(id)
    }
}

class GetExerciseWithTargets(
    private val repository: ExerciseRepository
) {
    operator fun invoke(): Flow<List<ExerciseWithTargets>> {
        return repository.getExerciseWithTargets()
    }
}

class InsertExercise(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exercise: Exercise) {
        return repository.insertExercise(exercise)
    }
}

class UpdateExercise(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exercise: Exercise) {
        return repository.updateExercise(exercise)
    }
}

class DeleteExercise(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exercise: Exercise) {
        return repository.deleteExercise(exercise)
    }
}

data class ExerciseUseCases(
    val getExercises: GetExercises,
    val getExerciseById: GetExerciseById,
    val insertExercise: InsertExercise,
    val updateExercise: UpdateExercise,
    val deleteExercise: DeleteExercise,
    val getExerciseWithTargets: GetExerciseWithTargets
)