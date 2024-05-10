package com.leeweeder.luxthenics.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Exercise(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}