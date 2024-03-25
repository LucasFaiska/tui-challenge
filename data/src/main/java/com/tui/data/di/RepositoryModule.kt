package com.tui.data.di

import com.tui.data.repository.ChallengeRepositoryImpl
import com.tui.data.source.remote.CodewarsApi
import com.tui.domain.repository.ChallengeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideChallengeRepository(codewarsApi: CodewarsApi): ChallengeRepository {
        return ChallengeRepositoryImpl(codewarsApi)
    }

}