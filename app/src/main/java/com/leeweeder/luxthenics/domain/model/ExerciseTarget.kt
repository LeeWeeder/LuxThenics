package com.leeweeder.luxthenics.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.Relation

@Entity(
    foreignKeys = [ForeignKey(
        entity = Exercise::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("exerciseId")
    ), ForeignKey(
        entity = Target::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("targetId")
    )], primaryKeys = ["exerciseId", "targetId"],
    indices = [Index("targetId")]
)
data class ExerciseTargetCrossRef(
    val exerciseId: Int,
    val targetId: Int
)

data class ExerciseWithTargets(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ExerciseTargetCrossRef::class,
            parentColumn = "exerciseId",
            entityColumn = "targetId"
        )
    )
    val targets: List<Target>
)

data class TargetWithExercises(
    @Embedded val target: Target,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ExerciseTargetCrossRef::class,
            parentColumn = "targetId",
            entityColumn = "exerciseId"
        )
    )
    val exercises: List<Exercise>
)
