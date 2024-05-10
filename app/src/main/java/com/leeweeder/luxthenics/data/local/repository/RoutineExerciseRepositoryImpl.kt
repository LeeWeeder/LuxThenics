package com.leeweeder.luxthenics.data.local.repository

import com.leeweeder.luxthenics.data.local.database.RoutineExerciseDao
import com.leeweeder.luxthenics.domain.model.RoutineExercise
import com.leeweeder.luxthenics.domain.repository.RoutineExerciseRepository
import kotlinx.coroutines.flow.Flow

class RoutineExerciseRepositoryImpl(
    private val dao: RoutineExerciseDao
): RoutineExerciseRepository {
    override fun getRoutineExercises(): Flow<List<RoutineExercise>> {
        return dao.getRoutineExercises()
    }

    override fun getRoutineExerciseById(id: Int): RoutineExercise? {
        return dao.getRoutineExerciseById(id)
    }

    override suspend fun insertRoutineExercise(routineExercise: RoutineExercise) {
        dao.insertRoutineExercise(routineExercise)
    }

    override suspend fun updateRoutineExercise(routineExercise: RoutineExercise) {
        dao.updateRoutineExercise(routineExercise)
    }

    override suspend fun deleteRoutineExercise(routineExercise: RoutineExercise) {
        dao.deleteRoutineExercise(routineExercise)
    }

    override fun getRoutineExercisesByRoutineId(routineId: Int): Flow<List<RoutineExercise>> {
        return dao.getRoutineExercisesByRoutineId(routineId)
    }
}