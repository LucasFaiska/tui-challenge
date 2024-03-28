package com.tui.data.repository

import com.tui.data.mapper.toChallenge
import com.tui.data.mapper.toCompletedChallenge
import com.tui.data.source.remote.CodewarsApi
import com.tui.data.source.remote.core.NetworkResponse
import com.tui.data.source.remote.dto.ChallengeResponse
import com.tui.data.source.remote.dto.CompletedChallengeResponse
import com.tui.data.source.remote.dto.ListResponse
import com.tui.domain.model.Challenge
import com.tui.domain.model.CompletedChallenge
import com.tui.domain.model.PageListData
import com.tui.domain.repository.ChallengeRepository
import com.tui.domain.repository.RepositoryResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ChallengeRepositoryImpl(
    private val codewarsApi: CodewarsApi,
    coroutineDispatcher: CoroutineDispatcher
) : ChallengeRepository {

    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)

    override suspend fun getCompletedChallenges(
        username: String,
        page: Int
    ): RepositoryResult<PageListData<CompletedChallenge>> =
        with(scope.coroutineContext) {
            runCatching {
                handleCompletedChallengesResponse(
                    codewarsApi.getCompletedChallenges(username, page)
                )
            }.getOrElse {
                RepositoryResult.Error
            }
        }

    private fun handleCompletedChallengesResponse(
        response: NetworkResponse<ListResponse<CompletedChallengeResponse>>
    ): RepositoryResult<PageListData<CompletedChallenge>> {
        return when (response) {
            is NetworkResponse.Success -> {
                val completedChallengeResponse = response.body
                RepositoryResult.Success(
                    PageListData(
                        completedChallengeResponse.totalPages,
                        completedChallengeResponse.totalItems,
                        completedChallengeResponse.data.map {
                            it.toCompletedChallenge()
                        }
                    )
                )
            }

            is NetworkResponse.Error -> RepositoryResult.Error
        }
    }

    override suspend fun getChallengeDetails(
        challengeId: String
    ): RepositoryResult<Challenge> =
        with(scope.coroutineContext) {
            runCatching {
                handleChallengeDetailsResponse(
                    codewarsApi.getChallengeDetails(challengeId)
                )
            }.getOrElse {
                RepositoryResult.Error
            }
        }

    private fun handleChallengeDetailsResponse(
        response: NetworkResponse<ChallengeResponse>
    ): RepositoryResult<Challenge> {
        return when (response) {
            is NetworkResponse.Success -> {
                RepositoryResult.Success(response.body.toChallenge())
            }

            is NetworkResponse.Error -> RepositoryResult.Error
        }
    }

}