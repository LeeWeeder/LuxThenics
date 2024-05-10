package com.leeweeder.luxthenics.presentation.new_exercise

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.leeweeder.luxthenics.domain.usecases.ExerciseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewExerciseViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases
) : ViewModel() {
    private val _newExerciseUiState = mutableStateOf(NewExerciseUiState())
    val newExerciseUiState: State<NewExerciseUiState> = _newExerciseUiState

    fun onEvent(event: NewExerciseEvent) {
        when (event) {
            is NewExerciseEvent.ExerciseNameOnValueChange -> {
                _newExerciseUiState.value = newExerciseUiState.value.copy(
                    exerciseName = event.exerciseName
                )
            }

            NewExerciseEvent.AddProgressionButtonClick -> {
                _newExerciseUiState.value = newExerciseUiState.value.copy(
                    newProgressionDialogVisible = true
                )
            }

            is NewExerciseEvent.AddSubProgressionButtonClick -> {
                _newExerciseUiState.value = newExerciseUiState.value.copy(
                    newSubProgressionDialogVisible = true,
                    newSubProgressionState = newExerciseUiState.value.newSubProgressionState.copy(
                        index = event.index
                    )
                )
            }

            NewExerciseEvent.NewProgressionDialogDismiss -> {
                _newExerciseUiState.value = newExerciseUiState.value.copy(
                    newProgressionDialogVisible = false,
                    newProgressionName = ""
                )
            }

            NewExerciseEvent.NewSubProgressionDialogDismiss -> {
                _newExerciseUiState.value = newExerciseUiState.value.copy(
                    newSubProgressionDialogVisible = false,
                    newSubProgressionState = newExerciseUiState.value.newSubProgressionState.copy(
                        newSubProgressionName = ""
                    )
                )
            }

            is NewExerciseEvent.NewProgressionNameOnValueChange -> {
                _newExerciseUiState.value = newExerciseUiState.value.copy(
                    newProgressionName = event.newProgressionName
                )
            }

            is NewExerciseEvent.NewSubProgressionNameOnValueChange -> {
                _newExerciseUiState.value = newExerciseUiState.value.copy(
                    newSubProgressionState = newExerciseUiState.value.newSubProgressionState.copy(
                        newSubProgressionName = event.newSubProgressionName
                    )
                )
            }

            NewExerciseEvent.InsertProgression -> {
                _newExerciseUiState.value = newExerciseUiState.value.copy(
                    progressions = newExerciseUiState.value.progressions.toMutableList()
                        .also {
                            it.add(
                                Pair(
                                    ProgressionName(newExerciseUiState.value.newProgressionName),
                                    emptyList()
                                )
                            )
                        }
                        .toList(),
                    newProgressionName = ""
                )
            }

            NewExerciseEvent.InsertSubProgression -> {
                _newExerciseUiState.value = newExerciseUiState.value.copy(
                    progressions = newExerciseUiState.value.progressions.toMutableList()
                        .also { pair ->
                            pair[newExerciseUiState.value.newSubProgressionState.index] = pair[newExerciseUiState.value.newSubProgressionState.index].copy(
                                second = pair[newExerciseUiState.value.newSubProgressionState.index].second.toMutableList()
                                    .also {
                                        it.add(
                                            ProgressionName(newExerciseUiState.value.newSubProgressionState.newSubProgressionName)
                                        )
                                    }.toList()
                            )
                        }
                        .toList(),
                    newSubProgressionState = newExerciseUiState.value.newSubProgressionState.copy(
                        newSubProgressionName = ""
                    )
                )
            }
        }
    }
}