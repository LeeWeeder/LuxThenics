package com.leeweeder.luxthenics.presentation.new_exercise

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leeweeder.luxthenics.R
import com.leeweeder.luxthenics.ui.LocalLuxThenicsNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NewExerciseScreen(
    viewModel: NewExerciseViewModel = hiltViewModel()
) {
    val navController = LocalLuxThenicsNavController.current

    val newExerciseUiState = viewModel.newExerciseUiState.value

    val focusManager = LocalFocusManager.current

    val exerciseNameFocusRequester = remember {
        FocusRequester()
    }
    val progressionNameFocusRequester = remember {
        FocusRequester()
    }
    val subProgressionNameFocusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(key1 = true) {
        exerciseNameFocusRequester.requestFocus()
    }

    if (newExerciseUiState.newProgressionDialogVisible) {
        LaunchedEffect(key1 = true) {
            focusManager.clearFocus()
            progressionNameFocusRequester.requestFocus()
        }
        AlertDialog(
            onDismissRequest = { viewModel.onEvent(NewExerciseEvent.NewProgressionDialogDismiss) },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.onEvent(NewExerciseEvent.InsertProgression)
                        viewModel.onEvent(NewExerciseEvent.NewProgressionDialogDismiss)
                    }) {
                    Text(text = "Add")
                }
            },
            title = {
                Text(text = "New progression")
            },
            text = {
                OutlinedTextField(value = newExerciseUiState.newProgressionName, onValueChange = {
                    viewModel.onEvent(NewExerciseEvent.NewProgressionNameOnValueChange(it))
                }, modifier = Modifier.focusRequester(progressionNameFocusRequester))
            })
    }

    if (newExerciseUiState.newSubProgressionDialogVisible) {
        LaunchedEffect(key1 = true) {
            subProgressionNameFocusRequester.requestFocus()
        }
            AlertDialog(
            onDismissRequest = { viewModel.onEvent(NewExerciseEvent.NewSubProgressionDialogDismiss) },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.onEvent(NewExerciseEvent.InsertSubProgression)
                        viewModel.onEvent(NewExerciseEvent.NewSubProgressionDialogDismiss)
                    }) {
                    Text(text = "Add")
                }
            },
            title = {
                Text(text = "New sub progression")
            },
            text = {
                OutlinedTextField(
                    value = newExerciseUiState.newSubProgressionState.newSubProgressionName,
                    onValueChange = {
                        viewModel.onEvent(NewExerciseEvent.NewSubProgressionNameOnValueChange(it))
                    }, modifier = Modifier.focusRequester(subProgressionNameFocusRequester))
            })
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "New exercise") }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "Close icon"
                    )
                }
            }, actions = {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Save")
                }
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            OutlinedTextField(
                value = newExerciseUiState.exerciseName, onValueChange = {
                    viewModel.onEvent(NewExerciseEvent.ExerciseNameOnValueChange(it))
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .padding(horizontal = 16.dp)
                    .focusRequester(exerciseNameFocusRequester), label = {
                    Text(text = "Exercise name")
                }, singleLine = true
            )
            ListItem(
                headlineContent = { Text(text = "Category") },
                trailingContent = { Text(text = "None") },
                modifier = Modifier.clickable { })
            ListItem(
                headlineContent = { Text(text = "Target") },
                trailingContent = { Text(text = "None") })
            LazyColumn(contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                val progressions = newExerciseUiState.progressions
                val progressionCount = progressions.size
                items(progressionCount, key = { it }) { index ->
                    var visible by remember {
                        mutableStateOf(false)
                    }
                    val dismissState = rememberSwipeToDismissBoxState()
                    SwipeToDismissBox(state = dismissState, backgroundContent = {}) {
                        ElevatedCard(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .animateItemPlacement()
                        ) {
                            ElevatedCard(
                                onClick = { visible = !visible },
                                shape = RoundedCornerShape(
                                    topStart = 12.dp,
                                    topEnd = 12.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 0.dp
                                )
                            ) {
                                ListItem(
                                    headlineContent = { Text(text = progressions[index].first.value) },
                                    leadingContent = {
                                        Box(contentAlignment = Alignment.Center) {
                                            CircularProgressIndicator(
                                                progress = {
                                                    ((index + 1) / progressionCount.toFloat())
                                                },
                                                strokeCap = StrokeCap.Round,
                                                trackColor = MaterialTheme.colorScheme.primary.copy(
                                                    alpha = 0.2f
                                                ),
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                            Text(
                                                text = "${index + 1}",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontFamily = FontFamily.Cursive,
                                                fontWeight = FontWeight.Black
                                            )
                                        }
                                    },
                                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                    trailingContent = {
                                        val rotation by animateFloatAsState(
                                            targetValue = if (visible) -180f else 0f,
                                            label = "expand_icon_rotation"
                                        )
                                        Icon(
                                            painter = painterResource(R.drawable.expand_more),
                                            contentDescription = null,
                                            modifier = Modifier.rotate(rotation)
                                        )
                                    }
                                )
                            }

                            AnimatedVisibility(visible = visible) {
                                Column {
                                    val subProgressions = progressions[index].second
                                    val subProgressionCount = subProgressions.size
                                    println(subProgressionCount)
                                    repeat(subProgressionCount) {
                                        val dismissState2 = rememberSwipeToDismissBoxState()
                                        SwipeToDismissBox(
                                            state = dismissState2,
                                            backgroundContent = {}) {
                                            ElevatedCard(shape = RectangleShape) {
                                                ListItem(
                                                    headlineContent = { Text(text = subProgressions[it].value) },
                                                    leadingContent = {
                                                        Box(contentAlignment = Alignment.Center) {
                                                            CircularProgressIndicator(
                                                                progress = {
                                                                    (it + 1) / subProgressionCount.toFloat()
                                                                },
                                                                strokeCap = StrokeCap.Round,
                                                                trackColor = MaterialTheme.colorScheme.secondary.copy(
                                                                    alpha = 0.2f
                                                                ),
                                                                color = MaterialTheme.colorScheme.secondary
                                                            )
                                                            Text(
                                                                text = "${index + 1}.${it + 1}",
                                                                style = MaterialTheme.typography.titleMedium,
                                                                fontFamily = FontFamily.Cursive,
                                                                fontWeight = FontWeight.Black
                                                            )
                                                        }
                                                    },
                                                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                                    modifier = Modifier.padding(horizontal = 16.dp)
                                                )
                                            }
                                        }
                                    }
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            viewModel.onEvent(
                                                NewExerciseEvent.AddSubProgressionButtonClick(
                                                    index
                                                )
                                            )
                                        }
                                        .height(ButtonDefaults.MinHeight),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.add),
                                            contentDescription = null,
                                            modifier = Modifier.size(ButtonDefaults.IconSize),
                                            tint = MaterialTheme.colorScheme.secondary
                                        )
                                        Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                                        Text(
                                            text = "Add sub progression",
                                            color = MaterialTheme.colorScheme.secondary,
                                            style = MaterialTheme.typography.labelLarge
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                item(key = progressionCount + 1) {
                    ElevatedButton(
                        onClick = {
                            viewModel.onEvent(NewExerciseEvent.AddProgressionButtonClick)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                        Text(text = "Add progression")
                    }
                }
            }
        }
    }
}