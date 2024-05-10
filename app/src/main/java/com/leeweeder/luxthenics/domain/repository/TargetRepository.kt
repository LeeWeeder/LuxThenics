package com.leeweeder.luxthenics.domain.repository

import com.leeweeder.luxthenics.domain.model.Target
import com.leeweeder.luxthenics.domain.model.TargetWithExercises
import kotlinx.coroutines.flow.Flow

interface TargetRepository {
    fun getTargets(): Flow<List<Target>>

    fun getTargetById(id: Int): Target?

    suspend fun insertTarget(target: Target)

    suspend fun updateTarget(target: Target)

    suspend fun deleteTarget(target: Target)

    fun getTargetWithRoutines(id: Int): List<TargetWithExercises>
}