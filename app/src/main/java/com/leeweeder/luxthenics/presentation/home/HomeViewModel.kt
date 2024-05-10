package com.leeweeder.luxthenics.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leeweeder.luxthenics.domain.usecases.RoutineUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val routineUseCases: RoutineUseCases
) : ViewModel() {
    private val _homeUiState = mutableStateOf(HomeUiState())
    val homeUiState: State<HomeUiState> = _homeUiState

    private var getRoutinesJob: Job? = null

    init {
        getRoutines()
    }

    fun onEvent(event: HomeEvent) {
    }

    private fun getRoutines() {
        getRoutinesJob?.cancel()
        getRoutinesJob = routineUseCases.getRoutines()
            .onEach { routines ->
                _homeUiState.value = homeUiState.value.copy(
                    routines = routines
                )
            }
            .launchIn(viewModelScope)
    }
}