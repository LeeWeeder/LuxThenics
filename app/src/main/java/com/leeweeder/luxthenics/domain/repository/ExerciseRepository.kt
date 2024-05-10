package com.leeweeder.luxthenics.domain.repository

import com.leeweeder.luxthenics.domain.model.Exercise
import com.leeweeder.luxthenics.domain.model.ExerciseWithTargets
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun getExercises(): Flow<List<Exercise>>

    fun getExerciseById(id: Int): Exercise?

    suspend fun insertExercise(exercise: Exercise)

    suspend fun updateExercise(exercise: Exercise)

    suspend fun deleteExercise(exercise: Exercise)

    fun getExerciseWithTargets(): Flow<List<ExerciseWithTargets>>
}