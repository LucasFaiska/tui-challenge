package com.tui.challenge.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.tui.challenge.routes.Destinations
import com.tui.challenge.routes.Destinations.CHALLENGE_DETAILS_ROUTE
import com.tui.challenge.routes.Destinations.COMPLETED_CHALLENGES_ROUTE

class NavigationActions(navController: NavHostController) {

    val navigateToCompletedChallenges: () -> Unit = {
        navController.navigate(COMPLETED_CHALLENGES_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    val navigateToChallengeDetails: (String) -> Unit = { challengeId ->
        navController.navigate("${CHALLENGE_DETAILS_ROUTE}/$challengeId") {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

}