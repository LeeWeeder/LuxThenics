package com.leeweeder.luxthenics.data.local.repository

import com.leeweeder.luxthenics.data.local.database.RoutineDao
import com.leeweeder.luxthenics.domain.model.Routine
import com.leeweeder.luxthenics.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow

class RoutineRepositoryImpl(
    private val dao: RoutineDao
): RoutineRepository {
    override fun getRoutines(): Flow<List<Routine>> {
        return dao.getRoutines()
    }

    override fun getRoutineById(id: Int): Routine? {
        return dao.getRoutineById(id)
    }

    override suspend fun insertRoutine(routine: Routine) {
        dao.insertRoutine(routine)
    }

    override suspend fun updateRoutine(routine: Routine) {
        dao.updateRoutine(routine)
    }

    override suspend fun deleteRoutine(routine: Routine) {
        dao.deleteRoutine(routine)
    }
}