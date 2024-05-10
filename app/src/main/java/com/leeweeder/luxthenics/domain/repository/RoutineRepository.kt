package com.leeweeder.luxthenics.domain.repository

import com.leeweeder.luxthenics.domain.model.Routine
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {
    fun getRoutines(): Flow<List<Routine>>

    fun getRoutineById(id: Int): Routine?

    suspend fun insertRoutine(routine: Routine)

    suspend fun updateRoutine(routine: Routine)

    suspend fun deleteRoutine(routine: Routine)
}