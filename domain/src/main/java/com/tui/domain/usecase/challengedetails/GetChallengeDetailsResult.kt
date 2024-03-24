package com.tui.domain.usecase.challengedetails

import com.tui.domain.model.Challenge

sealed class GetChallengeDetailsResult {

    data class Success(val data: Challenge) : GetChallengeDetailsResult()

    data object Error : GetChallengeDetailsResult()

    data object ChallengeIdEmpty : GetChallengeDetailsResult()
}