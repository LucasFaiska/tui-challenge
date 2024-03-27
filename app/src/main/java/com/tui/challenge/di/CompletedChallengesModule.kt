package com.tui.challenge.di

import com.tui.domain.repository.ChallengeRepository
import com.tui.domain.usecase.completedchallenges.GetCompletedChallengesFromUser
import com.tui.domain.usecase.completedchallenges.GetCompletedChallengesFromUserImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CompletedChallengesModule {

    @Singleton
    @Provides
    fun provideGetCompletedChallengesFromUserUseCase(
        repository: ChallengeRepository
    ): GetCompletedChallengesFromUser {
        return GetCompletedChallengesFromUserImpl(repository)
    }

}