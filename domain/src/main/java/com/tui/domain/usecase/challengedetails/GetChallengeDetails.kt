package com.tui.domain.usecase.challengedetails

import com.tui.domain.repository.ChallengeRepository
import com.tui.domain.repository.RepositoryResult

interface GetChallengeDetails {
    suspend operator fun invoke(challengeId: String): GetChallengeDetailsResult
}

class GetChallengeDetailsImpl(
    private val challengeRepository: ChallengeRepository
) : GetChallengeDetails {
    override suspend fun invoke(challengeId: String): GetChallengeDetailsResult {
        if (challengeId.isBlank()) return GetChallengeDetailsResult.ChallengeIdEmpty
        challengeRepository.getChallengeDetails(challengeId).let { result ->
            return when (result) {
                is RepositoryResult.Success -> {
                    GetChallengeDetailsResult.Success(result.data)
                }

                else -> GetChallengeDetailsResult.Error
            }
        }
    }
}
