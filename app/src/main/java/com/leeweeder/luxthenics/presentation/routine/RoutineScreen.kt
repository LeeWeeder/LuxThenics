package com.leeweeder.luxthenics.presentation.routine

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leeweeder.luxthenics.R
import com.leeweeder.luxthenics.presentation.components.LuxThenicsBasicTextField
import com.leeweeder.luxthenics.presentation.util.KeyBoardState
import com.leeweeder.luxthenics.presentation.util.keyBoardAsState
import com.leeweeder.luxthenics.ui.LocalLuxThenicsNavController
import com.leeweeder.luxthenics.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineScreen(
    viewModel: RoutineViewModel = hiltViewModel()
) {
    BackHandler {
        viewModel.onEvent(RoutineEvent.CancelCreateNewRoutine)
    }
    val navController = LocalLuxThenicsNavController.current

    val routineUiState = viewModel.routineUiState.value

    val routineName by remember {
        mutableStateOf(routineUiState.routineName)
    }

    var routineNameTextField by remember {
        mutableStateOf(TextFieldValue(routineName))
    }

    val focusManager = LocalFocusManager.current
    val focusRequester = remember {
        FocusRequester()
    }
    val keyBoardState by keyBoardAsState()

    LaunchedEffect(key1 = keyBoardState) {
        if (keyBoardState == KeyBoardState.Hidden) {
            focusManager.clearFocus()
        }
    }

    CancelCreateNewRoutineDialog(
        visible = routineUiState.cancelCreateNewRoutineDialogVisible,
        onDismissRequest = { viewModel.onEvent(RoutineEvent.CancelCreateNewRoutineCancellation) },
        onConfirm = {
            navController.popBackStack()
        }
    )

    if (routineUiState.savingRoutineAlertDialogState?.visible == true) {
        AlertDialog(
            onDismissRequest = { viewModel.onEvent(RoutineEvent.DismissSavingRoutineDialog) },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.onEvent(RoutineEvent.DismissSavingRoutineDialog)
                    focusRequester.requestFocus()
                }) {
                    Text(text = "Okay")
                }
            },
            text = {
                Text(text = routineUiState.savingRoutineAlertDialogState.text)
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "New routine")
            }, navigationIcon = {
                IconButton(onClick = {
                    if (routineUiState.routineName.isEmpty()) {
                        navController.popBackStack()
                    } else {
                        viewModel.onEvent(RoutineEvent.CancelCreateNewRoutine)
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "Close icon"
                    )
                }
            }, actions = {
                TextButton(onClick = {
                    viewModel.onEvent(RoutineEvent.SaveRoutineButtonClick)
                }) {
                    Text(text = "Save")
                }
            }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = paddingValues.calculateStartPadding(
                    LayoutDirection.Ltr
                ) + 16.dp,
                vertical = paddingValues.calculateTopPadding()
            )
        ) {
            item {
                LuxThenicsBasicTextField(value = routineNameTextField, onValueChange = {
                    routineNameTextField = it
                    viewModel.onEvent(
                        RoutineEvent.OnRoutineNameTextFieldValueChange(
                            routineNameTextField.text
                        )
                    )
                }, placeholder = "Add routine name", modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            routineNameTextField = routineNameTextField.copy(
                                selection = TextRange(
                                    0,
                                    routineUiState.routineName.length
                                )
                            )
                        }
                    }
                )
            }
            item {
                TextButton(
                    onClick = { navController.navigate(Screen.ExercisesScreen.route) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                    Text(text = "Add exercise")
                }
            }
        }
    }
}

@Composable
internal fun CancelCreateNewRoutineDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    if (visible) {
        AlertDialog(onDismissRequest = onDismissRequest, confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Discard", color = MaterialTheme.colorScheme.error)
            }
        }, text = {
            Text(text = "Are you sure you want to discard this routine? This process cannot be undone.")
        }, title = {
            Text(text = "Discard routine?")
        }, dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Cancel")
            }
        })
    }
}