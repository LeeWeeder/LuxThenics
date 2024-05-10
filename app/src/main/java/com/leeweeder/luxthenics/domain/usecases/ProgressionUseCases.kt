package com.leeweeder.luxthenics.domain.usecases

import com.leeweeder.luxthenics.domain.model.Progression
import com.leeweeder.luxthenics.domain.repository.ProgressionRepository
import kotlinx.coroutines.flow.Flow

class GetProgressionsByExerciseId(
    private val repository: ProgressionRepository
) {
    operator fun invoke(exerciseId: Int): Flow<List<Progression>> {
        return repository.getProgressionsByExerciseId(exerciseId)
    }
}

class InsertProgression(
    private val repository: ProgressionRepository
) {
    suspend operator fun invoke(progression: Progression) {
        return repository.insertProgression(progression)
    }
}

class UpdateProgression(
    private val repository: ProgressionRepository
) {
    suspend operator fun invoke(progression: Progression) {
        return repository.updateProgression(progression)
    }
}


class DeleteProgression(
    private val repository: ProgressionRepository
) {
    suspend operator fun invoke(progression: Progression) {
        return repository.deleteProgression(progression)
    }
}

data class ProgressionUseCases(
    val getProgressionsByExerciseId: GetProgressionsByExerciseId,
    val insertProgression: InsertProgression,
    val updateProgression: UpdateProgression,
    val deleteProgression: DeleteProgression
)