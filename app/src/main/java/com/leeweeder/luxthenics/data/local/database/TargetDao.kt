package com.leeweeder.luxthenics.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.leeweeder.luxthenics.domain.model.Target
import com.leeweeder.luxthenics.domain.model.TargetWithExercises
import kotlinx.coroutines.flow.Flow

@Dao
interface TargetDao {
    @Query("SELECT * FROM target")
    fun getTargets(): Flow<List<Target>>

    @Query("SELECT * FROM target WHERE id = :id")
    fun getTargetById(id: Int): Target?

    @Transaction
    @Query("SELECT * FROM target WHERE id = :id")
    fun getTargetWithRoutine(id: Int): List<TargetWithExercises>

    @Insert
    suspend fun insertTarget(target: Target)

    @Update
    suspend fun updateTarget(target: Target)

    @Delete
    suspend fun deleteTarget(target: Target)
}