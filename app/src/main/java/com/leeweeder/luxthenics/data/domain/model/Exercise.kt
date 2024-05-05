package com.leeweeder.luxthenics.data.domain.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Exercise(
    val name: String,
    val note: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise ORDER BY id DESC LIMIT 10")
    fun getExercises(): Flow<List<Exercise>>

    @Insert
    suspend fun insertExercise(item: Exercise)
}
