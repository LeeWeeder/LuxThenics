package com.leeweeder.luxthenics.data.local.repository

import com.leeweeder.luxthenics.data.local.database.TargetDao
import com.leeweeder.luxthenics.domain.model.Target
import com.leeweeder.luxthenics.domain.model.TargetWithExercises
import com.leeweeder.luxthenics.domain.repository.TargetRepository
import kotlinx.coroutines.flow.Flow

class TargetRepositoryImpl(
    private val dao: TargetDao
): TargetRepository {
    override fun getTargets(): Flow<List<Target>> {
        return dao.getTargets()
    }

    override fun getTargetById(id: Int): Target? {
        return dao.getTargetById(id)
    }

    override suspend fun insertTarget(target: Target) {
        dao.insertTarget(target)
    }

    override suspend fun updateTarget(target: Target) {
        dao.updateTarget(target)
    }

    override suspend fun deleteTarget(target: Target) {
        dao.deleteTarget(target)
    }

    override fun getTargetWithRoutines(id: Int): List<TargetWithExercises> {
        return dao.getTargetWithRoutine(id)
    }
}