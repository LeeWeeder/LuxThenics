package com.leeweeder.luxthenics.domain.repository

import com.leeweeder.luxthenics.domain.model.ExerciseGroup
import kotlinx.coroutines.flow.Flow

interface ExerciseGroupRepository {
    fun getExerciseGroupById(id: Int): Flow<List<ExerciseGroup>>

    suspend fun insertExerciseGroup(exerciseGroup: ExerciseGroup)

    suspend fun updateExerciseGroup(exerciseGroup: ExerciseGroup)

    suspend fun deleteExerciseGroup(exerciseGroup: ExerciseGroup)
}