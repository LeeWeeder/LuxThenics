package com.leeweeder.luxthenics.presentation.home

import com.leeweeder.luxthenics.domain.model.Routine

data class HomeUiState(
    val routines: List<Routine> = emptyList()
)