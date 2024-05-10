package com.leeweeder.luxthenics.presentation.new_exercise

sealed class NewExerciseEvent {
    data class ExerciseNameOnValueChange(val exerciseName: String): NewExerciseEvent()
    data class AddSubProgressionButtonClick(val index: Int): NewExerciseEvent()
    data object AddProgressionButtonClick: NewExerciseEvent()
    data object NewProgressionDialogDismiss: NewExerciseEvent()
    data object NewSubProgressionDialogDismiss: NewExerciseEvent()
    data class NewProgressionNameOnValueChange(val newProgressionName: String): NewExerciseEvent()
    data class NewSubProgressionNameOnValueChange(val newSubProgressionName: String): NewExerciseEvent()
    data object InsertProgression: NewExerciseEvent()
    data object InsertSubProgression: NewExerciseEvent()
}
