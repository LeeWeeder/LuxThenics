package com.leeweeder.luxthenics.data.local.repository

import com.leeweeder.luxthenics.data.local.database.ExerciseGroupDao
import com.leeweeder.luxthenics.domain.model.ExerciseGroup
import com.leeweeder.luxthenics.domain.repository.ExerciseGroupRepository
import kotlinx.coroutines.flow.Flow

class ExerciseGroupRepositoryImpl(
    private val dao: ExerciseGroupDao
): ExerciseGroupRepository {
    override fun getExerciseGroupById(id: Int): Flow<List<ExerciseGroup>> {
        return dao.getExerciseGroupById(id)
    }

    override suspend fun insertExerciseGroup(exerciseGroup: ExerciseGroup) {
        dao.insertExerciseGroup(exerciseGroup)
    }

    override suspend fun updateExerciseGroup(exerciseGroup: ExerciseGroup) {
        dao.updateExerciseGroup(exerciseGroup)
    }

    override suspend fun deleteExerciseGroup(exerciseGroup: ExerciseGroup) {
        dao.deleteExerciseGroup(exerciseGroup)
    }

}