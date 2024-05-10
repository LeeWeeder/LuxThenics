package com.leeweeder.luxthenics.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leeweeder.luxthenics.presentation.exercises.ExerciseScreen
import com.leeweeder.luxthenics.presentation.home.HomeScreen
import com.leeweeder.luxthenics.presentation.new_exercise.NewExerciseScreen
import com.leeweeder.luxthenics.presentation.routine.RoutineScreen
import com.leeweeder.luxthenics.utils.Screen

val LocalLuxThenicsNavController =
    compositionLocalOf<NavHostController> { error("NavHostController error") }

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    val (durationMillis, easing) = Pair(350, EaseInOut)

    val enterTransition: @JvmSuppressWildcards (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
        {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(durationMillis = durationMillis, easing = easing)
            )
        }

    val exitTransition: @JvmSuppressWildcards (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
        {
            fadeOut(
                targetAlpha = 0.5f,
                animationSpec = tween(durationMillis = durationMillis, easing = easing)
            ) + slideOut(animationSpec = tween(durationMillis = durationMillis, easing = easing)) {
                IntOffset(-150, 0)
            }
        }

    val popExitTransition: @JvmSuppressWildcards (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
        {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(durationMillis = durationMillis, easing = easing)
            )
        }

    val popEnterTransition: @JvmSuppressWildcards (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
        {
            fadeIn(
                initialAlpha = 0.5f,
                animationSpec = tween(durationMillis = durationMillis, easing = easing)
            ) + slideIn(animationSpec = tween(durationMillis = durationMillis, easing = easing)) {
                IntOffset(-150, 0)
            }
        }

    CompositionLocalProvider(LocalLuxThenicsNavController provides navController) {
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
            composable(
                Screen.HomeScreen.route,
                popEnterTransition = popEnterTransition,
                exitTransition = exitTransition
            ) { HomeScreen() }
            composable(
                Screen.RoutineScreen.route,
                enterTransition = enterTransition,
                popEnterTransition = popEnterTransition,
                exitTransition = exitTransition,
                popExitTransition = popExitTransition
            ) { RoutineScreen() }
            composable(
                Screen.ExercisesScreen.route,
                enterTransition = enterTransition,
                exitTransition = exitTransition,
                popEnterTransition = popEnterTransition,
                popExitTransition = popExitTransition
            ) { ExerciseScreen() }
            composable(
                Screen.NewExerciseScreen.route,
                enterTransition = enterTransition,
                exitTransition = exitTransition,
                popEnterTransition = popEnterTransition,
                popExitTransition = popExitTransition
            ) { NewExerciseScreen() }
        }
    }
}
