package com.tui.challenge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigationComponent(
    navigator: Navigator,
    navigationActions: NavigationActions
) {
    val launchedEffectNavigationLabel = "navigation"

    LaunchedEffect(launchedEffectNavigationLabel) {
        navigator.navTarget.onEach { navTarget ->
            when (navTarget) {
                is NavTarget.Splash -> {
                    navigationActions.navigateToDestination(navTarget.destination)
                }

                is NavTarget.CompletedChallenges -> {
                    navigationActions.navigateToDestination(
                        navTarget.destination,
                        true,
                        Destination.Splash
                    )
                }

                is NavTarget.ChallengeDetails -> {
                    navigationActions.navigateToDestinationWithData(
                        navTarget.destination,
                        navTarget.data
                    )
                }
            }
        }.launchIn(this)
    }
}