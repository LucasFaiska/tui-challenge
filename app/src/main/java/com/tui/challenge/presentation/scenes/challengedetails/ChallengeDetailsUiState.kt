package com.tui.challenge.presentation.scenes.challengedetails

import com.tui.domain.model.Challenge

sealed class ChallengeDetailsUiState {
    data object Loading : ChallengeDetailsUiState()
    class Success(val challenge: Challenge) : ChallengeDetailsUiState()
    data object Error : ChallengeDetailsUiState()
}