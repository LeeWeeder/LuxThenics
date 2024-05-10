package com.leeweeder.luxthenics.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.leeweeder.luxthenics.utils.ExerciseType
import com.leeweeder.luxthenics.utils.Volume

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("exerciseId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Routine::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("routineId"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ExerciseGroup::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("exerciseGroupId")
        )
    ], indices = [Index("exerciseGroupId"), Index("routineId"), Index("exerciseId")]
)
data class RoutineExercise(
    val exerciseId: Int,
    val routineId: Int,
    val setCount: Int,
    val restTimeMillis: Long,
    val exerciseType: ExerciseType,
    val volume: Volume,
    val exerciseGroupId: Int?,
    val note: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}