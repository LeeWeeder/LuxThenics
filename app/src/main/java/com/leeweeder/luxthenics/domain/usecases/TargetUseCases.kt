package com.leeweeder.luxthenics.domain.usecases

import com.leeweeder.luxthenics.domain.model.Target
import com.leeweeder.luxthenics.domain.model.TargetWithExercises
import com.leeweeder.luxthenics.domain.repository.TargetRepository
import kotlinx.coroutines.flow.Flow

class GetTargets(
    private val repository: TargetRepository
) {
    operator fun invoke(): Flow<List<Target>> {
        return repository.getTargets()
    }
}

class GetTargetById(
    private val repository: TargetRepository
) {
    operator fun invoke(id: Int): Target? {
        return repository.getTargetById(id)
    }
}

class GetTargetWithRoutines(
    private val repository: TargetRepository
) {
    operator fun invoke(id: Int): List<TargetWithExercises> {
        return repository.getTargetWithRoutines(id)
    }
}

class InsertTarget(
    private val repository: TargetRepository
) {
    suspend operator fun invoke(routine: Target) {
        return repository.insertTarget(routine)
    }
}

class UpdateTarget(
    private val repository: TargetRepository
) {
    suspend operator fun invoke(routine: Target) {
        return repository.updateTarget(routine)
    }
}

class DeleteTarget(
    private val repository: TargetRepository
) {
    suspend operator fun invoke(routine: Target) {
        return repository.deleteTarget(routine)
    }
}

data class TargetUseCases(
    val getTargets: GetTargets,
    val getTargetById: GetTargetById,
    val insertTarget: InsertTarget,
    val updateTarget: UpdateTarget,
    val deleteTarget: DeleteTarget,
    val getTargetWithRoutines: GetTargetWithRoutines
)