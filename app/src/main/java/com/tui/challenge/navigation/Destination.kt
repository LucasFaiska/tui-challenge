package com.tui.challenge.navigation

sealed class Destination(
    val route: String,
    val params: List<String> = emptyList()
) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    data object Splash : Destination(SPLASH_ROUTE)

    data object CompletedChallenges : Destination(COMPLETED_CHALLENGES_ROUTE)

    data object ChallengeDetails : Destination(
        CHALLENGE_DETAILS_ROUTE,
        listOf(CHALLENGE_DETAILS_ROUTE_CHALLENGE_ID)
    )

    companion object {
        private const val SPLASH_ROUTE = "splash"
        private const val COMPLETED_CHALLENGES_ROUTE = "completedChallenges"
        private const val CHALLENGE_DETAILS_ROUTE = "challengeDetails"
        private const val CHALLENGE_DETAILS_ROUTE_CHALLENGE_ID = "challengeId"
    }
}