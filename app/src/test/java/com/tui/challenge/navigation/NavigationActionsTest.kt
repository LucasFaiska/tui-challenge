package com.tui.challenge.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class NavigationActionsTest {

    @MockK(relaxed = true)
    private lateinit var navHostController: NavHostController

    private lateinit var navigationActions: NavigationActions

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        navigationActions = NavigationActions(navHostController)
    }

    @Test
    fun `Given a destination, When navigateToDestination without pop up is called, Then navigate to destination`() {
        val destination = Destination.Splash
        val expectedNavOptions = NavOptions.Builder().build()

        navigationActions.navigateToDestination(destination)

        verify { navHostController.navigate(destination.route, expectedNavOptions) }
    }

    @Test
    fun `Given a destination, When navigateToDestination with pop up is called, Then navigate to destination with pop up`() {
        val destination = Destination.Splash
        val popUpTo = true
        val popUpToDestination = Destination.CompletedChallenges
        val expectedNavOptions = NavOptions.Builder().setPopUpTo(popUpToDestination.route, true).build()

        navigationActions.navigateToDestination(destination, popUpTo, popUpToDestination)

        verify { navHostController.navigate(destination.route, expectedNavOptions) }
    }

    @Test
    fun `Given a destination and data, When navigateToDestinationWithData without pop up is called, Then navigate to destination with data`() {
        val destination = Destination.Splash
        val data = listOf("data1", "data2")
        val concatenatedData = data.joinToString("/")
        val url = "${destination.route}/$concatenatedData"
        val expectedNavOptions = NavOptions.Builder().build()

        navigationActions.navigateToDestinationWithData(destination, data)

        verify { navHostController.navigate(url, expectedNavOptions) }
    }

    @Test
    fun `Given a destination and data, When navigateToDestinationWithData with pop up is called, Then navigate to destination with data and pop up`() {
        val destination = Destination.Splash
        val data = listOf("data1", "data2")
        val concatenatedData = data.joinToString("/")
        val url = "${destination.route}/$concatenatedData"
        val popUpTo = true
        val popUpToDestination = Destination.CompletedChallenges
        val expectedNavOptions = NavOptions.Builder().setPopUpTo(popUpToDestination.route, true).build()

        navigationActions.navigateToDestinationWithData(destination, data, popUpTo, popUpToDestination)

        verify { navHostController.navigate(url, expectedNavOptions) }
    }
}