package com.leeweeder.luxthenics.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.leeweeder.luxthenics.utils.Volume
import java.util.Date

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoutineExercise::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("routineExerciseId")
    ), ForeignKey(
        entity = Progression::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("progressionId")
    )], indices = [Index("progressionId"), Index("routineExerciseId")]
)
data class ExerciseLog(
    val progressionId: Int,
    val date: Date,
    val volume: Volume.Fixed,
    val weight: Float,
    val setNumber: Int = 1,
    val note: String?,
    val routineExerciseId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}