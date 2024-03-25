package com.tui.data.utils

import com.tui.data.source.remote.dto.ChallengeResponse
import com.tui.data.source.remote.dto.CompletedChallengeResponse
import com.tui.data.source.remote.dto.ListResponse
import com.tui.data.source.remote.dto.RankResponse
import com.tui.data.source.remote.dto.UserResponse
import com.tui.domain.model.Challenge
import com.tui.domain.model.CompletedChallenge
import com.tui.domain.model.PageListData
import com.tui.domain.model.Rank
import com.tui.domain.model.User

class Mocks {

    companion object {

        val completedChallengesResponseMock = ListResponse(
            totalPages = 1,
            totalItems = 1,
            listOf(
                CompletedChallengeResponse(
                    id = "514b92a657cdc65150000006",
                    name = "Multiples of 3 and 5",
                    slug = "multiples-of-3-and-5",
                    completedAt = Utils.stringToDate("2017-04-06T16:32:09Z"),
                    completedLanguages = listOf(
                        "javascript",
                        "coffeescript",
                        "ruby",
                        "javascript",
                        "ruby",
                        "javascript",
                        "ruby",
                        "coffeescript",
                        "javascript",
                        "ruby",
                        "coffeescript"
                    )
                )
            )
        )

        val challengeResponseMock = ChallengeResponse(
            id = "5277c8a221e209d3f6000b56",
            name = "Valid Braces",
            slug = "valid-braces",
            url = "http://www.codewars.com/kata/valid-braces",
            category = "algorithms",
            description = "Write a function called `validBraces` that takes a string ...",
            tags = listOf("Algorithms", "Validation", "Logic", "Utilities"),
            languages = listOf("javascript", "coffeescript"),
            rank = RankResponse(
                id = -4,
                name = "4 kyu",
                color = "blue"
            ),
            createdBy = UserResponse(
                username = "xDranik",
                url = "http://www.codewars.com/users/xDranik"
            ),
            approvedBy = UserResponse(
                username = "xDranik",
                url = "http://www.codewars.com/users/xDranik"
            ),
            totalAttempts = 4911,
            totalCompleted = 919,
            totalStars = 12,
            voteScore = 512,
            publishedAt = Utils.stringToDate("2013-11-05T00:07:31Z"),
            approvedAt = Utils.stringToDate("2013-12-20T14:53:06Z")
        )

        val completedChallengesMock = PageListData(
            totalPages = 1,
            totalItems = 1,
            listOf(
                CompletedChallenge(
                    id = "514b92a657cdc65150000006",
                    name = "Multiples of 3 and 5",
                    slug = "multiples-of-3-and-5",
                    completedAt = Utils.stringToDate("2017-04-06T16:32:09Z"),
                    completedLanguages = listOf(
                        "javascript",
                        "coffeescript",
                        "ruby",
                        "javascript",
                        "ruby",
                        "javascript",
                        "ruby",
                        "coffeescript",
                        "javascript",
                        "ruby",
                        "coffeescript"
                    )
                )
            )
        )

        val challengeMock = Challenge(
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
            publishedAt = Utils.stringToDate("2013-11-05T00:07:31Z"),
            approvedAt = Utils.stringToDate("2013-12-20T14:53:06Z")
        )
    }
}