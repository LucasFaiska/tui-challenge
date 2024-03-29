package com.tui.challenge.navigation

import org.junit.Test

class NavTargetTest {

    @Test
    fun `Given a Splash NavTarget, When it is created, Then it should have the correct destination`() {
        val navTarget = NavTarget.Splash
        val destination = navTarget.destination
        assert(destination == Destination.Splash)
    }

    @Test
    fun `Given a CompletedChallenges NavTarget, When it is created, Then it should have the correct destination`() {
        val navTarget = NavTarget.CompletedChallenges
        val destination = navTarget.destination
        assert(destination == Destination.CompletedChallenges)
    }

    @Test
    fun `Given a ChallengeDetails NavTarget, When it is created, Then it should have the correct destination`() {
        val challengeId = "challengeId"
        val navTarget = NavTarget.ChallengeDetails(challengeId)
        val destination = navTarget.destination
        assert(destination == Destination.ChallengeDetails)
        assert(navTarget.data[0] == challengeId)
    }
}