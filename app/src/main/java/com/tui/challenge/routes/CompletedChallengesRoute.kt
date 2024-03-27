package com.tui.challenge.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.tui.challenge.navigation.NavigationActions
import com.tui.challenge.presentation.ui.challenges.completed.CompletedChallengesScreen
import com.tui.challenge.presentation.ui.challenges.completed.CompletedChallengesViewModel

@Composable
fun CompletedChallengesRoute(navigationActions: NavigationActions) {
    val viewModel = hiltViewModel<CompletedChallengesViewModel>()

    CompletedChallengesScreen(
        uiState = viewModel.uiState.collectAsState().value,
        onCompletedChallengesUiEvent = viewModel::onEvent,
        navigationActions = navigationActions
    )
}