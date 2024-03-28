package com.tui.challenge.di

import com.tui.domain.repository.ChallengeRepository
import com.tui.domain.usecase.challengedetails.GetChallengeDetails
import com.tui.domain.usecase.challengedetails.GetChallengeDetailsImpl
import com.tui.domain.usecase.completedchallenges.GetCompletedChallengesFromUser
import com.tui.domain.usecase.completedchallenges.GetCompletedChallengesFromUserImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCompletedChallengesFromUserUseCase(
        repository: ChallengeRepository
    ): GetCompletedChallengesFromUser {
        return GetCompletedChallengesFromUserImpl(repository)
    }

    @Provides
    fun provideGetChallengeDetailsUseCase(
        repository: ChallengeRepository
    ): GetChallengeDetails {
        return GetChallengeDetailsImpl(repository)
    }
}