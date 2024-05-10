package com.leeweeder.luxthenics.presentation.routine

data class RoutineUiState(
    val routineName: String = "",
    val cancelCreateNewRoutineDialogVisible: Boolean = false,
    val savingRoutineAlertDialogState: SavingRoutineAlertDialogState? = null
)

data class SavingRoutineAlertDialogState(
    val visible: Boolean = false,
    val text: String = ""
)