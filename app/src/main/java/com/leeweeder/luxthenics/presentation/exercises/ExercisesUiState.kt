package com.leeweeder.luxthenics.presentation.exercises

import com.leeweeder.luxthenics.domain.model.ExerciseWithTargets

data class ExercisesUiState(
    val exerciseWithTargets: List<ExerciseWithTargets> = emptyList(),
    val exercisesTopAppBarMode: ExercisesTopAppBarMode = ExercisesTopAppBarMode.Default,
    val moreDropDownMenuExpanded: Boolean = false,
)

enum class ExercisesTopAppBarMode {
    Search,
    Default
}
