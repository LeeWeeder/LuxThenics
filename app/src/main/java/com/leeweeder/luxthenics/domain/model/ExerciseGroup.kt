package com.leeweeder.luxthenics.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerciseGroup(
    val symbol: Char
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
