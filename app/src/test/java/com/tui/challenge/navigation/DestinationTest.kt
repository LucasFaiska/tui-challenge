package com.tui.challenge.navigation

import org.junit.Test

class DestinationTest {

    @Test
    fun `Given Destination Splash, When Destination is created, Then the full route is as expected`() {
        val destination = Destination.Splash
        val expectedFullRoute = "splash"
        assert(destination.fullRoute == expectedFullRoute)
    }

    @Test
    fun `Given Destination CompletedChallenges, When Destination is created, Then the full route is as expected`() {
        val destination = Destination.CompletedChallenges
        val expectedFullRoute = "completedChallenges"
        assert(destination.fullRoute == expectedFullRoute)
    }

    @Test
    fun `Given Destination ChallengeDetails, When Destination is created, Then the full route is as expected`() {
        val destination = Destination.ChallengeDetails
        val expectedFullRoute = "challengeDetails/{challengeId}"
        assert(destination.fullRoute == expectedFullRoute)
    }

}