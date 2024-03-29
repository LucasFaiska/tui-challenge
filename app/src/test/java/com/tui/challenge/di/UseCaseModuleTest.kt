package com.tui.challenge.di

import com.tui.domain.usecase.challengedetails.GetChallengeDetailsImpl
import com.tui.domain.usecase.completedchallenges.GetCompletedChallengesFromUserImpl
import io.mockk.mockk
import org.junit.Test

class UseCaseModuleTest {

    @Test
    fun `Given a UseCaseModule, When provideGetCompletedChallengesFromUserUseCase, Then returns the expected GetCompletedChallengesUseCase setup`() {
        val providedUseCase =
            UseCaseModule.provideGetCompletedChallengesFromUserUseCase(
                mockk()
            )

        assert(providedUseCase is GetCompletedChallengesFromUserImpl)
    }

    @Test
    fun `Given a UseCaseModule, When provideGetChallengeDetailsUseCase, Then returns the expected GetChallengeDetailsUseCase setup`() {
        val providedUseCase = UseCaseModule.provideGetChallengeDetailsUseCase(
            mockk()
        )

        assert(providedUseCase is GetChallengeDetailsImpl)
    }

}