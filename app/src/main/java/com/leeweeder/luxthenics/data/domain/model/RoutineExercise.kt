package com.leeweeder.luxthenics.data.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
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
    ]
)
data class RoutineExercise(
    val exerciseId: Int,
    val routineId: Int,
    val setCount: Int,
    val restTimeMillis: Long,
    val exerciseType: ExerciseType,
    val volume: Volume,
    val exerciseGroupId: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}