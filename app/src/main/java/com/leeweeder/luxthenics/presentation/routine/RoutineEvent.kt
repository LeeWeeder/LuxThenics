package com.leeweeder.luxthenics.presentation.routine

sealed class RoutineEvent {
    data object CancelCreateNewRoutine: RoutineEvent()
    data object CancelCreateNewRoutineCancellation: RoutineEvent()
    data object SaveRoutineButtonClick: RoutineEvent()
    data object DismissSavingRoutineDialog: RoutineEvent()
    data object ShowSavingRoutineAlertDialog: RoutineEvent()
    data class OnRoutineNameTextFieldValueChange(val routineName: String): RoutineEvent()
}