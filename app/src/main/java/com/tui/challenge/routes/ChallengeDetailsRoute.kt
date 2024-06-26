package com.tui.challenge.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.tui.challenge.presentation.scenes.challengedetails.ChallengeDetailsScreen
import com.tui.challenge.presentation.scenes.challengedetails.ChallengeDetailsViewModel

@Composable
fun ChallengeDetailsRoute(challengeId: String) {
    val viewModel = hiltViewModel<ChallengeDetailsViewModel>()

    LaunchedEffect(Unit) {
        viewModel.loadChallengeDetails(challengeId)
    }

    ChallengeDetailsScreen(
        uiState = viewModel.uiState.collectAsState().value,
        onChallengeDetailsUiEvent = viewModel::onEvent
    )
}