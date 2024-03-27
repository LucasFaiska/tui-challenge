package com.tui.challenge.presentation.scenes.challengedetails

sealed class ChallengeDetailsUiEvent {
    data class OnRetryButtonClick(val challengeId: String) : ChallengeDetailsUiEvent()
}