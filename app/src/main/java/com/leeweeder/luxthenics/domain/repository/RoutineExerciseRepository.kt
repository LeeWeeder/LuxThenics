package com.leeweeder.luxthenics.domain.repository

import com.leeweeder.luxthenics.domain.model.RoutineExercise
import kotlinx.coroutines.flow.Flow

interface RoutineExerciseRepository {
    fun getRoutineExercises(): Flow<List<RoutineExercise>>

    fun getRoutineExerciseById(id: Int): RoutineExercise?

    suspend fun insertRoutineExercise(routineExercise: RoutineExercise)

    suspend fun updateRoutineExercise(routineExercise: RoutineExercise)

    suspend fun deleteRoutineExercise(routineExercise: RoutineExercise)

    fun getRoutineExercisesByRoutineId(routineId: Int): Flow<List<RoutineExercise>>
}