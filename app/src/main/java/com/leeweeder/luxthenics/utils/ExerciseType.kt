package com.leeweeder.luxthenics.utils

import androidx.room.TypeConverter

sealed class ExerciseType(val symbol: String) {
    data object Timed: ExerciseType(symbol = "timer")
    data object Repetition: ExerciseType(symbol = "repeat")
}