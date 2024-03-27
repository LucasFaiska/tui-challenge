package com.tui.challenge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tui.challenge.routes.CompletedChallengesRoute
import com.tui.challenge.routes.Destinations.COMPLETED_CHALLENGES_ROUTE
import com.tui.challenge.routes.Destinations.SPLASH_ROUTE
import com.tui.challenge.routes.SplashRoute

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = SPLASH_ROUTE,
    navigationActions: NavigationActions
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = SPLASH_ROUTE) {
            SplashRoute(navigationActions = navigationActions)
        }

        composable(route = COMPLETED_CHALLENGES_ROUTE) {
            CompletedChallengesRoute(navigationActions = navigationActions)
        }
    }
}