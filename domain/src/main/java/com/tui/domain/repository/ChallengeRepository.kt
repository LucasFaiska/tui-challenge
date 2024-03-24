package com.tui.domain.repository

import com.tui.domain.model.Challenge
import com.tui.domain.model.CompletedChallenge
import com.tui.domain.model.PageListData

interface ChallengeRepository {
    fun getCompletedChallenges(username: String, page: Int): RepositoryResult<PageListData<CompletedChallenge>>
    fun getChallengeDetails(challengeId: String): RepositoryResult<Challenge>
}