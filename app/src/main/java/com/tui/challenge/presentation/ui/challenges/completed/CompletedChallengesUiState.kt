package com.tui.challenge.presentation.ui.challenges.completed

import com.tui.domain.model.CompletedChallenge

sealed class CompletedChallengesUiState {
    data object Loading : CompletedChallengesUiState()
    data class Success(
        val totalItems: Int,
        val challenges: List<CompletedChallenge>,
        val isLoadingMore: Boolean
    ) : CompletedChallengesUiState()
    data object Error : CompletedChallengesUiState()
}