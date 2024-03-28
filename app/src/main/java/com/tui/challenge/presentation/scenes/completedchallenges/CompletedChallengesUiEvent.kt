package com.tui.challenge.presentation.scenes.completedchallenges

sealed class CompletedChallengesUiEvent {
    data class OnListScrolledToBottom(
        val currentPage: Int
    ) : CompletedChallengesUiEvent()

    data object OnRetryButtonClick : CompletedChallengesUiEvent()

    data class OnChallengeClick(val challengeId: String) : CompletedChallengesUiEvent()
}