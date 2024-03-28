package com.tui.challenge.navigation

import android.util.Log
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
        Log.d("NavigationComponent", "LaunchedEffect initiated")
        navigator.navTarget.onEach { navTarget ->
            Log.d("NavigationComponent", "navTarget: $navTarget")
            when (navTarget) {
                is NavTarget.Splash -> {
                    navigationActions.navigateToDestination(navTarget.destination)
                }

                is NavTarget.CompletedChallenges -> {
                    navigationActions.navigateToDestination(navTarget.destination)
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