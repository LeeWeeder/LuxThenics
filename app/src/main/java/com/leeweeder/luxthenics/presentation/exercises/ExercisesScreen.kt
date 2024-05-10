package com.leeweeder.luxthenics.presentation.exercises

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.leeweeder.luxthenics.R
import com.leeweeder.luxthenics.presentation.components.LuxThenicsBasicTextField
import com.leeweeder.luxthenics.ui.LocalLuxThenicsNavController
import com.leeweeder.luxthenics.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScreen(
    viewModel: ExercisesViewModel = hiltViewModel()
) {
    val navController = LocalLuxThenicsNavController.current
    val exercisesUiState = viewModel.exercisesUiState.value
    val topAppBarMode = exercisesUiState.exercisesTopAppBarMode

    val focusRequester = remember {
        FocusRequester()
    }

    Scaffold(topBar = {
        when (topAppBarMode) {
            ExercisesTopAppBarMode.Search -> {
                var value by remember {
                    mutableStateOf("")
                }
                LaunchedEffect(key1 = topAppBarMode) {
                    focusRequester.requestFocus()
                }

                Column {
                    TopAppBar(
                        title = {
                            LuxThenicsBasicTextField(
                                value = value,
                                onValueChange = {
                                    value = it
                                },
                                placeholder = "Search",
                                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                modifier = Modifier.focusRequester(focusRequester)
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                viewModel.onEvent(
                                    ExercisesEvent.SetTopAppBarMode(
                                        ExercisesTopAppBarMode.Default
                                    )
                                )
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_back),
                                    contentDescription = "Go back icon"
                                )
                            }
                        },
                        actions = {
                            FilterIconButton(
                                onClick = { /*TODO*/ }
                            )
                        }
                    )
                    HorizontalDivider()
                }
            }

            ExercisesTopAppBarMode.Default -> {
                LargeTopAppBar(title = { Text(text = "Add exercises") }, navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Close icon"
                        )
                    }
                }, actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(ExercisesEvent.SetTopAppBarMode(ExercisesTopAppBarMode.Search))
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Search icon"
                        )
                    }
                    FilterIconButton(onClick = { /*TODO*/ })
                    Box {
                        IconButton(onClick = { viewModel.onEvent(ExercisesEvent.MoreIconButtonClick) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.more_vert),
                                contentDescription = "More icon"
                            )
                        }
                        DropdownMenu(
                            expanded = exercisesUiState.moreDropDownMenuExpanded,
                            onDismissRequest = { viewModel.onEvent(ExercisesEvent.MoreDropDownMenuDismiss) }) {
                            DropdownMenuItem(
                                text = { Text(text = "Create exercise") },
                                onClick = {
                                    viewModel.onEvent(ExercisesEvent.MoreDropDownMenuDismiss)
                                    navController.navigate(Screen.NewExerciseScreen.route)
                                },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.add),
                                        contentDescription = null
                                    )
                                })
                        }
                    }
                }
                )
            }
        }
    }) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(exercisesUiState.exerciseWithTargets) { exerciseWithTargets ->
                ListItem(headlineContent = { exerciseWithTargets.exercise.name })
            }
        }
    }
}

@Composable
internal fun FilterIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.filter_list),
            contentDescription = "Filter icon"
        )
    }
}