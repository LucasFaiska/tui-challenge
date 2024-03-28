package com.tui.challenge.navigation

sealed class NavTarget(
    val destination: Destination,
    val data: List<String> = emptyList()
) {
    data object Splash : NavTarget(Destination.Splash)

    data object CompletedChallenges : NavTarget(Destination.CompletedChallenges)

    data class ChallengeDetails(
        val challengeId: String
    ) : NavTarget(Destination.ChallengeDetails, listOf(challengeId))
}