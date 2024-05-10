package com.leeweeder.luxthenics.presentation.new_exercise

@JvmInline
value class ProgressionName(val value: String)

data class NewSubProgressionState(
    val index: Int = 0,
    val newSubProgressionName: String = ""
)

data class NewExerciseUiState(
    val exerciseName: String = "",
    val newProgressionDialogVisible: Boolean = false,
    val newSubProgressionDialogVisible: Boolean = false,
    val newProgressionName: String = "",
    val newSubProgressionState: NewSubProgressionState = NewSubProgressionState(),
    val progressions: List<Pair<ProgressionName, List<ProgressionName>>> = emptyList()
)