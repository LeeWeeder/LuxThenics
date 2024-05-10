package com.leeweeder.luxthenics.data.local.database

import androidx.room.TypeConverter
import com.leeweeder.luxthenics.utils.ExerciseType
import com.leeweeder.luxthenics.utils.Volume
import java.util.Date

class Converters {
    @TypeConverter
    fun fromVolume(volume: Volume): String {
        return when (volume) {
            is Volume.Fixed -> "Fixed,${volume.volume}"
            is Volume.Range -> "Range,${volume.from},${volume.to}"
        }
    }

    @TypeConverter
    fun toVolume(data: String): Volume {
        val parts = data.split(",")
        return when (parts[0]) {
            "Fixed" -> Volume.Fixed(parts[1].toLong())
            "Range" -> Volume.Range(parts[1].toLong(), parts[2].toLong())
            else -> throw IllegalArgumentException("Unknown volume type")
        }
    }

    @TypeConverter
    fun fromFixedVolume(volume: Volume.Fixed): Long {
        return volume.volume
    }

    @TypeConverter
    fun toFixedVolume(volume: Long): Volume.Fixed {
        return Volume.Fixed(volume)
    }

    @TypeConverter
    fun fromExerciseType(type: ExerciseType): String {
        return when (type) {
            ExerciseType.Repetition -> "Repetition"
            ExerciseType.Timed -> "Timed"
        }
    }

    @TypeConverter
    fun toExerciseType(name: String): ExerciseType {
        return when (name) {
            "Timed" -> ExerciseType.Timed
            "Repetition" -> ExerciseType.Repetition
            else -> throw IllegalArgumentException("Unknown exercise type")
        }
    }

    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}