package com.tui.challenge.utils

import com.tui.domain.model.CompletedChallenge

val completedChallengesPreviewMock = listOf(
    CompletedChallenge(
        id = "123456",
        name = "Challenge 1",
        slug = "challenge-1",
        completedAt = "01/01/2021 12:00:00",
        completedLanguages = listOf(
            "java",
            "kotlin",
            "python",
            "javascript",
            "python",
            "javascript",
            "python",
            "javascript",
            "python",
            "javascript"
        )
    ),
    CompletedChallenge(
        id = "789012",
        name = "Challenge 2",
        slug = "challenge-2",
        completedAt = "01/01/2021 12:00:00",
        completedLanguages = listOf("python", "javascript")
    ),
    CompletedChallenge(
        id = "345678",
        name = "Challenge 3",
        slug = "challenge-3",
        completedAt = "01/01/2021 12:00:00",
        completedLanguages = listOf("c#", "swift")
    )
)