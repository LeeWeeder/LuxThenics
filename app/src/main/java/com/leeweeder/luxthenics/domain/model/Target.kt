package com.leeweeder.luxthenics.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Target(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
