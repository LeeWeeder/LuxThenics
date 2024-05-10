package com.leeweeder.luxthenics.presentation.exercises

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leeweeder.luxthenics.domain.usecases.ExerciseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases
) : ViewModel() {
    private val _exercisesUiState = mutableStateOf(ExercisesUiState())
    val exercisesUiState: State<ExercisesUiState> = _exercisesUiState

    private var getExerciseWithTargetsJob: Job? = null

    init {
        getExerciseWithTargets()
    }

    fun onEvent(event: ExercisesEvent) {
        when (event) {
            is ExercisesEvent.SetTopAppBarMode -> {
                _exercisesUiState.value = exercisesUiState.value.copy(
                    exercisesTopAppBarMode = event.mode
                )
            }

            ExercisesEvent.MoreIconButtonClick -> {
                _exercisesUiState.value = exercisesUiState.value.copy(
                    moreDropDownMenuExpanded = true
                )
            }

            ExercisesEvent.MoreDropDownMenuDismiss -> {
                _exercisesUiState.value = exercisesUiState.value.copy(
                    moreDropDownMenuExpanded = false
                )
            }
        }
    }

    private fun getExerciseWithTargets() {
        getExerciseWithTargetsJob?.cancel()
        getExerciseWithTargetsJob = exerciseUseCases.getExerciseWithTargets()
            .onEach { exerciseWithTargets ->
                _exercisesUiState.value = exercisesUiState.value.copy(
                    exerciseWithTargets = exerciseWithTargets
                )
            }
            .launchIn(viewModelScope)
    }
}