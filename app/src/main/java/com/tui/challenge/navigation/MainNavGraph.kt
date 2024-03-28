package com.tui.challenge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tui.challenge.routes.ChallengeDetailsRoute
import com.tui.challenge.routes.CompletedChallengesRoute
import com.tui.challenge.routes.SplashRoute

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Destination.Splash.fullRoute
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Destination.Splash.fullRoute) {
            SplashRoute()
        }

        composable(route = Destination.CompletedChallenges.fullRoute) {
            CompletedChallengesRoute()
        }

        composable(route = Destination.ChallengeDetails.fullRoute) {
            val challengeId = it.arguments
                ?.getString(Destination.ChallengeDetails.params.first())
                .orEmpty()

            ChallengeDetailsRoute(challengeId)
        }
    }
}