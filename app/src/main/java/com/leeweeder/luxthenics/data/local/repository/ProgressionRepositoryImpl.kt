package com.leeweeder.luxthenics.data.local.repository

import com.leeweeder.luxthenics.data.local.database.ProgressionDao
import com.leeweeder.luxthenics.domain.model.Progression
import com.leeweeder.luxthenics.domain.repository.ProgressionRepository
import kotlinx.coroutines.flow.Flow

class ProgressionRepositoryImpl(
    private val dao: ProgressionDao
): ProgressionRepository {
    override fun getProgressionsByExerciseId(exerciseId: Int): Flow<List<Progression>> {
        return dao.getAllProgressionByExerciseId(exerciseId)
    }

    override suspend fun insertProgression(progression: Progression) {
        dao.insertProgression(progression)
    }

    override suspend fun updateProgression(progression: Progression) {
        dao.updateProgression(progression)
    }

    override suspend fun deleteProgression(progression: Progression) {
        dao.deleteProgression(progression)
    }

}