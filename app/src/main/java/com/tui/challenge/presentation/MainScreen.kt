package com.tui.challenge.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.tui.challenge.navigation.MainNavGraph
import com.tui.challenge.navigation.NavigationActions
import com.tui.challenge.presentation.theme.AppTheme

@Composable
fun MainScreen() {
    AppTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            NavigationActions(navController)
        }

        MainNavGraph(
            navController = navController,
            navigationActions = navigationActions
        )
    }
}