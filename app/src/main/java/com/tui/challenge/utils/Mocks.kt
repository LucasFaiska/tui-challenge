package com.tui.challenge.utils

import com.tui.domain.model.Challenge
import com.tui.domain.model.CompletedChallenge
import com.tui.domain.model.Rank
import com.tui.domain.model.User
import java.util.Date

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

val challengeDetailsPreviewMock = Challenge(
    id = "5277c8a221e209d3f6000b56",
    name = "Valid Braces",
    slug = "valid-braces",
    url = "http://www.codewars.com/kata/valid-braces",
    category = "algorithms",
    description = "Write a function called `validBraces` that takes a string ...",
    tags = listOf("Algorithms", "Validation", "Logic", "Utilities"),
    languages = listOf("javascript", "coffeescript"),
    rank = Rank(
        id = -4,
        name = "4 kyu",
        color = "blue"
    ),
    createdBy = User(
        username = "xDranik",
        url = "http://www.codewars.com/users/xDranik"
    ),
    approvedBy = User(
        username = "xDranik",
        url = "http://www.codewars.com/users/xDranik"
    ),
    totalAttempts = 4911,
    totalCompleted = 919,
    totalStars = 12,
    voteScore = 512,
    publishedAt = Date(1383613651000),
    approvedAt = Date(1387555986000)
)