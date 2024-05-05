package com.leeweeder.luxthenics.data.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Progression::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("parentProgressionId"),
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Exercise::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("exerciseId")
    )],
    indices = [Index(value = ["name"], unique = true)]
)
data class Progression(
    val parentProgressionId: Int?,
    val level: Int,
    val name: Int,
    val exerciseId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
