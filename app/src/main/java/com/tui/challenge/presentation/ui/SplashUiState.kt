package com.tui.challenge.presentation.ui

sealed class SplashUiState {
    data object Loading: SplashUiState()
    data object LoadingFinished: SplashUiState()
}