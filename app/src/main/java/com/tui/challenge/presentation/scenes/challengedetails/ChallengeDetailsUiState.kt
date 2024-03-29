package com.tui.challenge.presentation.scenes.challengedetails

import com.tui.domain.model.Challenge

sealed class ChallengeDetailsUiState {
    data object Loading : ChallengeDetailsUiState()
    data class Success(val challenge: Challenge) : ChallengeDetailsUiState()
    data class Error(val challengeId: String) : ChallengeDetailsUiState()
}