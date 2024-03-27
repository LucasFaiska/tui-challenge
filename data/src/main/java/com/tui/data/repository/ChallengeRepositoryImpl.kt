package com.tui.data.repository

import com.tui.data.mapper.toChallenge
import com.tui.data.mapper.toCompletedChallenge
import com.tui.data.source.remote.CodewarsApi
import com.tui.data.source.remote.core.NetworkResponse
import com.tui.domain.model.Challenge
import com.tui.domain.model.CompletedChallenge
import com.tui.domain.model.PageListData
import com.tui.domain.repository.ChallengeRepository
import com.tui.domain.repository.RepositoryResult

class ChallengeRepositoryImpl(private val codewarsApi: CodewarsApi) : ChallengeRepository {
    override suspend fun getCompletedChallenges(
        username: String,
        page: Int
    ): RepositoryResult<PageListData<CompletedChallenge>> = runCatching {
        when (val networkResponse = codewarsApi.getCompletedChallenges(username, page)) {
            is NetworkResponse.Success -> {
                val completedChallengeResponse = networkResponse.body
                RepositoryResult.Success(
                    PageListData(
                        completedChallengeResponse.totalPages,
                        completedChallengeResponse.totalItems,
                        completedChallengeResponse.data.map { it.toCompletedChallenge() }
                    )
                )
            }

            is NetworkResponse.Error -> RepositoryResult.Error
        }
    }.getOrElse {
        RepositoryResult.Error
    }

    override suspend fun getChallengeDetails(challengeId: String): RepositoryResult<Challenge> = runCatching {
        when (val networkResponse = codewarsApi.getChallengeDetails(challengeId)) {
            is NetworkResponse.Success -> {
                val challengeResponse = networkResponse.body
                RepositoryResult.Success(challengeResponse.toChallenge())
            }

            is NetworkResponse.Error -> RepositoryResult.Error
        }
    }.getOrElse {
        RepositoryResult.Error
    }
}