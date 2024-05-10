package com.leeweeder.luxthenics.data.local.repository

import com.leeweeder.luxthenics.data.local.database.ExerciseDao
import com.leeweeder.luxthenics.domain.model.Exercise
import com.leeweeder.luxthenics.domain.model.ExerciseWithTargets
import com.leeweeder.luxthenics.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class ExerciseRepositoryImpl(
    private val dao: ExerciseDao
): ExerciseRepository {
    override fun getExercises(): Flow<List<Exercise>> {
        return dao.getExercises()
    }

    override fun getExerciseById(id: Int): Exercise? {
        return dao.getExerciseById(id)
    }

    override suspend fun insertExercise(exercise: Exercise) {
        dao.insertExercise(exercise)
    }

    override suspend fun updateExercise(exercise: Exercise) {
        dao.updateExercise(exercise)
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        dao.deleteExercise(exercise)
    }

    override fun getExerciseWithTargets(): Flow<List<ExerciseWithTargets>> {
        return dao.getExerciseWithTargets()
    }

}