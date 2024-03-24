package com.tui.domain.usecase.completedchallenges

import com.tui.domain.repository.ChallengeRepository
import com.tui.domain.repository.RepositoryResult

interface GetCompletedChallengesFromUser {
    suspend fun execute(username: String, page: Int): GetCompletedChallengesFromUserResult
}

class GetCompletedChallengesFromUserImpl(private val repository: ChallengeRepository) : GetCompletedChallengesFromUser {
    override suspend fun execute(username: String, page: Int): GetCompletedChallengesFromUserResult {
        if (username.isBlank()) return GetCompletedChallengesFromUserResult.UsernameEmpty
        return when (val result = repository.getCompletedChallenges(username, page)) {
            is RepositoryResult.Success -> GetCompletedChallengesFromUserResult.Success(result.data)
            else -> GetCompletedChallengesFromUserResult.Error
        }
    }
}