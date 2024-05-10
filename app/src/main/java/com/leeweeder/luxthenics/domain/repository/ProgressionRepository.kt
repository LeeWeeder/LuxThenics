package com.leeweeder.luxthenics.domain.repository

import com.leeweeder.luxthenics.domain.model.Progression
import kotlinx.coroutines.flow.Flow

interface ProgressionRepository {
    fun getProgressionsByExerciseId(exerciseId: Int): Flow<List<Progression>>

    suspend fun insertProgression(progression: Progression)

    suspend fun updateProgression(progression: Progression)

    suspend fun deleteProgression(progression: Progression)
}