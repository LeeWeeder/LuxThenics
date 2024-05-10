package com.leeweeder.luxthenics.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.leeweeder.luxthenics.domain.model.Routine
import com.leeweeder.luxthenics.domain.model.ExerciseWithTargets
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {
    @Query("SELECT * FROM routine")
    fun getRoutines(): Flow<List<Routine>>

    @Query("SELECT * FROM routine WHERE id = :id")
    fun getRoutineById(id: Int): Routine?

    @Insert
    suspend fun insertRoutine(routine: Routine)

    @Update
    suspend fun updateRoutine(routine: Routine)

    @Delete
    suspend fun deleteRoutine(routine: Routine)
}