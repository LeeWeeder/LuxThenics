package com.leeweeder.luxthenics.domain.usecases

import com.leeweeder.luxthenics.domain.model.ExerciseGroup
import com.leeweeder.luxthenics.domain.repository.ExerciseGroupRepository
import kotlinx.coroutines.flow.Flow

class GetExerciseGroupById(
    private val repository: ExerciseGroupRepository
) {
    operator fun invoke(id: Int): Flow<List<ExerciseGroup>> {
        return repository.getExerciseGroupById(id)
    }
}

class InsertExerciseGroup(
    private val repository: ExerciseGroupRepository
) {
    suspend operator fun invoke(exerciseGroup: ExerciseGroup) {
        return repository.insertExerciseGroup(exerciseGroup)
    }
}

class UpdateExerciseGroup(
    private val repository: ExerciseGroupRepository
) {
    suspend operator fun invoke(exerciseGroup: ExerciseGroup) {
        return repository.updateExerciseGroup(exerciseGroup)
    }
}

class DeleteExerciseGroup(
    private val repository: ExerciseGroupRepository
) {
    suspend operator fun invoke(exerciseGroup: ExerciseGroup) {
        return repository.deleteExerciseGroup(exerciseGroup)
    }
}

data class ExerciseGroupUseCases(
    val getExerciseGroupById: GetExerciseGroupById,
    val insertExerciseGroup: InsertExerciseGroup,
    val updateExerciseGroup: UpdateExerciseGroup,
    val deleteExerciseGroup: DeleteExerciseGroup
)