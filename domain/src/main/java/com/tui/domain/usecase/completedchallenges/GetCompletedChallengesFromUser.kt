package com.tui.domain.usecase.completedchallenges

import com.tui.domain.repository.ChallengeRepository
import com.tui.domain.repository.RepositoryResult

interface GetCompletedChallengesFromUser {
    suspend operator fun invoke(
        username: String,
        page: Int
    ): GetCompletedChallengesFromUserResult
}

class GetCompletedChallengesFromUserImpl(
    private val repository: ChallengeRepository
) : GetCompletedChallengesFromUser {
    override suspend operator fun invoke(
        username: String,
        page: Int
    ): GetCompletedChallengesFromUserResult {
        if (username.isBlank()) return GetCompletedChallengesFromUserResult.UsernameEmpty
        repository.getCompletedChallenges(username, page).let { result ->
            return when (result) {
                is RepositoryResult.Success -> {
                    GetCompletedChallengesFromUserResult.Success(result.data)
                }

                else -> GetCompletedChallengesFromUserResult.Error
            }
        }
    }
}