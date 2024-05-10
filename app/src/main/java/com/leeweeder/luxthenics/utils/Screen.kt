package com.leeweeder.luxthenics.utils

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("home_screen")
    data object RoutineScreen: Screen("routine_screen")
    data object ExercisesScreen: Screen("exercises_screen")
    data object NewExerciseScreen: Screen("new_exercise_screen")
}