package com.tui.data.di

import com.tui.data.repository.ChallengeRepositoryImpl
import org.junit.Test


class RepositoryModuleTest {

    @Test
    fun `Given a RepositoryModule, when provideChallengeRepository is called, then it should return a ChallengeRepositoryImpl`(){
        val expectedRepository = ChallengeRepositoryImpl(
            NetworkModule.provideCodewarsApi(
                NetworkModule.provideRetrofitInstance(
                    NetworkModule.provideHttpClient(),
                    NetworkModule.provideGson()
                )
            ),
            DispatchersModule.provideIoDispatcher()
        )

        val providedRepository = RepositoryModule.provideChallengeRepository(
            NetworkModule.provideCodewarsApi(
                NetworkModule.provideRetrofitInstance(
                    NetworkModule.provideHttpClient(),
                    NetworkModule.provideGson()
                )
            ),
            DispatchersModule.provideIoDispatcher()
        )

        assert(expectedRepository::class.java == providedRepository::class.java)
    }

}