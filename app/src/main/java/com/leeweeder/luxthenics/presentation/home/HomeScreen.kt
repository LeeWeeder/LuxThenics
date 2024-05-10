package com.leeweeder.luxthenics.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leeweeder.luxthenics.R
import com.leeweeder.luxthenics.ui.LocalLuxThenicsNavController
import com.leeweeder.luxthenics.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val navController = LocalLuxThenicsNavController.current

    val homeUiState = viewModel.homeUiState.value
    val routines = homeUiState.routines

    Scaffold(
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.End) {
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                    tooltip = {
                        PlainTooltip {
                            Text(
                                text = "Create new routine"
                            )
                        }
                    },
                    state = rememberTooltipState()
                ) {
                    SmallFloatingActionButton(
                        onClick = { navController.navigate(Screen.RoutineScreen.route) },
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Add icon"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                ExtendedFloatingActionButton(
                    text = { Text(text = "Start today's routine") },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.exercise),
                            contentDescription = null
                        )
                    },
                    onClick = { /*TODO*/ })
            }
        },
        topBar = { LargeTopAppBar(title = { Text(text = "Monday") }) }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(routines) {
                ListItem(headlineContent = { Text(text = it.name) })
            }
        }
    }
}