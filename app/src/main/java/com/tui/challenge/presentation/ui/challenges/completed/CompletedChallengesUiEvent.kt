package com.tui.challenge.presentation.ui.challenges.completed

sealed class CompletedChallengesUiEvent {
    data object OnListScrolledToBottom : CompletedChallengesUiEvent()
    data object OnRetryButtonClick : CompletedChallengesUiEvent()
}