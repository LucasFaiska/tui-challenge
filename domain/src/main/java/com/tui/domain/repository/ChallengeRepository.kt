package com.tui.domain.repository

import com.tui.domain.model.Challenge
import com.tui.domain.model.CompletedChallenge
import com.tui.domain.model.PageListData

interface ChallengeRepository {
    suspend fun getCompletedChallenges(username: String, page: Int): RepositoryResult<PageListData<CompletedChallenge>>
    suspend fun getChallengeDetails(challengeId: String): RepositoryResult<Challenge>
}