package com.tui.challenge.presentation.ui.challenges.completed

sealed class CompletedChallengesUiEvent {
    data class OnListScrolledToBottom(
        val currentPage: Int
    ) : CompletedChallengesUiEvent()

    data object OnRetryButtonClick : CompletedChallengesUiEvent()
}