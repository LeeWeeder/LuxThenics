package com.leeweeder.luxthenics.presentation.exercises

sealed class ExercisesEvent {
    data class SetTopAppBarMode(val mode: ExercisesTopAppBarMode): ExercisesEvent()
    data object MoreIconButtonClick: ExercisesEvent()
    data object MoreDropDownMenuDismiss: ExercisesEvent()
}