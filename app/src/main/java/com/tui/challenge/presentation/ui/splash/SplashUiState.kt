package com.tui.challenge.presentation.ui.splash

sealed class SplashUiState {
    data object Loading: SplashUiState()
    data object LoadingFinished: SplashUiState()
}