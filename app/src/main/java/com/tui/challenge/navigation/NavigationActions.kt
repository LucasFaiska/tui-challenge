package com.tui.challenge.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NavigationActions(private val navController: NavHostController) {

    fun navigateToDestination(destination: Destination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    fun navigateToDestinationWithData(
        destination: Destination,
        data: List<String>
    ) {
        val concatenatedData = data.joinToString("/")
        val url = "${destination.route}/$concatenatedData"
        navController.navigate(url) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

}