package com.leeweeder.luxthenics.presentation.routine

import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leeweeder.luxthenics.domain.model.Routine
import com.leeweeder.luxthenics.domain.usecases.RoutineUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(
    private val routineUseCases: RoutineUseCases
) : ViewModel() {
    private val _routineUiState = mutableStateOf(RoutineUiState())
    val routineUiState: State<RoutineUiState> = _routineUiState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: RoutineEvent) {
        when (event) {
            RoutineEvent.CancelCreateNewRoutine -> {
                _routineUiState.value =
                    routineUiState.value.copy(cancelCreateNewRoutineDialogVisible = true)
            }

            RoutineEvent.CancelCreateNewRoutineCancellation -> {
                _routineUiState.value =
                    routineUiState.value.copy(cancelCreateNewRoutineDialogVisible = false)
            }

            is RoutineEvent.SaveRoutineButtonClick -> {
                val routineName = _routineUiState.value.routineName.trim()
                if (routineName.isEmpty()) {
                    _routineUiState.value = routineUiState.value.copy(
                        savingRoutineAlertDialogState = SavingRoutineAlertDialogState(
                            visible = true,
                            text = "Routine name can't be empty. Please provide a name for your routine."
                        )
                    )
                    return
                }
                viewModelScope.launch {
                    try {
                        routineUseCases.insertRoutine(Routine(routineName))
                    } catch (e: SQLiteConstraintException) {
                        _routineUiState.value = routineUiState.value.copy(
                            savingRoutineAlertDialogState = SavingRoutineAlertDialogState(
                                visible = true,
                                text = "Routine name already exists. Please provide another name."
                            )
                        )
                    }
                }
            }

            RoutineEvent.DismissSavingRoutineDialog -> {
                _routineUiState.value = routineUiState.value.copy(
                    savingRoutineAlertDialogState = SavingRoutineAlertDialogState(visible = false)
                )
            }

            RoutineEvent.ShowSavingRoutineAlertDialog -> {
                _routineUiState.value = routineUiState.value.copy(
                    savingRoutineAlertDialogState = SavingRoutineAlertDialogState(visible = false)
                )
            }

            is RoutineEvent.OnRoutineNameTextFieldValueChange -> {
                _routineUiState.value = _routineUiState.value.copy(routineName = event.routineName)
            }
        }
    }

    sealed class UiEvent {
        data class SendMessage(val message: String) : UiEvent()
    }
}