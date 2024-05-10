package com.leeweeder.luxthenics.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.leeweeder.luxthenics.utils.Volume
import java.util.Date

@Entity
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