package com.leeweeder.luxthenics.domain.usecases

import com.leeweeder.luxthenics.domain.model.Routine
import com.leeweeder.luxthenics.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow

class GetRoutines(
    private val repository: RoutineRepository
) {
    operator fun invoke(): Flow<List<Routine>> {
        return repository.getRoutines()
    }
}

class GetRoutineById(
    private val repository: RoutineRepository
) {
    operator fun invoke(id: Int): Routine? {
        return repository.getRoutineById(id)
    }
}

class InsertRoutine(
    private val repository: RoutineRepository
) {
    suspend operator fun invoke(routine: Routine) {
        return repository.insertRoutine(routine)
    }
}

class UpdateRoutine(
    private val repository: RoutineRepository
) {
    suspend operator fun invoke(routine: Routine) {
        return repository.updateRoutine(routine)
    }
}

class DeleteRoutine(
    private val repository: RoutineRepository
) {
    suspend operator fun invoke(routine: Routine) {
        return repository.deleteRoutine(routine)
    }
}

data class RoutineUseCases(
    val getRoutines: GetRoutines,
    val getRoutineById: GetRoutineById,
    val insertRoutine: InsertRoutine,
    val updateRoutine: UpdateRoutine,
    val deleteRoutine: DeleteRoutine
)