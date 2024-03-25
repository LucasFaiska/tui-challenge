package com.tui.domain.usecase.challengedetails

import com.tui.domain.repository.ChallengeRepository
import com.tui.domain.repository.RepositoryResult
import com.tui.domain.utils.Mocks.Companion.challengeMock
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetChallengeDetailsTest {

    private lateinit var useCase: GetChallengeDetails

    @MockK
    private lateinit var repository: ChallengeRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetChallengeDetailsImpl(repository)
    }

    @Test
    fun `Given the challengeId is empty, when the use case is invoked, then return ChallengeIdEmpty`() {
        runBlocking {
            val result = useCase.invoke("")
            assertEquals(GetChallengeDetailsResult.ChallengeIdEmpty, result)
        }
    }

    @Test
    fun `Given the repository returns a challenge, When the use case is invoked, Then return Success with the Challenge from repository`() {
        runBlocking {
            val challengeId = "challengeId"
            coEvery { repository.getChallengeDetails(challengeId) } returns RepositoryResult.Success(challengeMock)
            val result = useCase.invoke(challengeId)
            assertEquals(GetChallengeDetailsResult.Success(challengeMock), result)
        }
    }

    @Test
    fun `Given the repository returns an error, When the use case is invoked, Then return Error`() {
        runBlocking {
            val challengeId = "challengeId"
            coEvery { repository.getChallengeDetails(challengeId) } returns RepositoryResult.Error
            val result = useCase.invoke(challengeId)
            assertEquals(GetChallengeDetailsResult.Error, result)
        }
    }


}