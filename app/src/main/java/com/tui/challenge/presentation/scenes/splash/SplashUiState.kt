package com.tui.challenge.presentation.scenes.splash

sealed class SplashUiState {
    data object Loading: SplashUiState()
    data object LoadingFinished: SplashUiState()
}