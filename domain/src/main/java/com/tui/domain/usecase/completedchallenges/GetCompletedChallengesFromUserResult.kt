package com.tui.domain.usecase.completedchallenges

import com.tui.domain.model.Challenge
import com.tui.domain.model.CompletedChallenge
import com.tui.domain.model.PageListData

sealed class GetCompletedChallengesFromUserResult {

    data class Success(val data: PageListData<CompletedChallenge>) : GetCompletedChallengesFromUserResult()

    data object Error : GetCompletedChallengesFromUserResult()

    data object UsernameEmpty : GetCompletedChallengesFromUserResult()
}