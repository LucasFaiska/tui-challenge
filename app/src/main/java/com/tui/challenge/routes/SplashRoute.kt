package com.tui.challenge.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.tui.challenge.navigation.NavigationActions
import com.tui.challenge.presentation.scenes.splash.SplashScreen
import com.tui.challenge.presentation.scenes.splash.SplashViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashRoute(navigationActions: NavigationActions) {
    val splashViewModel = hiltViewModel<SplashViewModel>()

    SplashScreen(
        uiState = splashViewModel.uiState.collectAsState().value,
        navigationActions = navigationActions
    )
}