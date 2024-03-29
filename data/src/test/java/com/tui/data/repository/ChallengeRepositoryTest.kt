package com.tui.data.repository

import com.tui.data.source.remote.CodewarsApi
import com.tui.data.source.remote.core.NetworkResponse
import com.tui.data.utils.Mocks.Companion.challengeMock
import com.tui.data.utils.Mocks.Companion.challengeResponseMock
import com.tui.data.utils.Mocks.Companion.completedChallengesMock
import com.tui.data.utils.Mocks.Companion.completedChallengesResponseMock
import com.tui.domain.repository.ChallengeRepository
import com.tui.domain.repository.RepositoryResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class ChallengeRepositoryTest {

    private lateinit var repository: ChallengeRepository

    @MockK
    private lateinit var codewarsApi: CodewarsApi

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository =
            ChallengeRepositoryImpl(codewarsApi, StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Given the api returns a list of completed challenges, When repository getCompletedChallenges is invoked, Then return Success with the list of completed challenges from api`() =
        runTest {
            val username = "username"
            val page = 1
            val completedChallenges = completedChallengesMock
            coEvery {
                codewarsApi.getCompletedChallenges(
                    username,
                    page
                )
            } returns NetworkResponse.Success(
                completedChallengesResponseMock
            )
            val result = repository.getCompletedChallenges(username, page)
            coVerify {
                codewarsApi.getCompletedChallenges(username, page)
            }
            assert(result is RepositoryResult.Success)
            assertEquals(
                (result as RepositoryResult.Success).data,
                completedChallenges
            )
        }

    @Test
    fun `Given the api returns an error, When repository getCompletedChallenges is invoked, Then return Error`() =
        runTest {
            val username = "username"
            val page = 1
            coEvery {
                codewarsApi.getCompletedChallenges(
                    username,
                    page
                )
            } returns NetworkResponse.Error
            val result = repository.getCompletedChallenges(username, page)
            coVerify {
                codewarsApi.getCompletedChallenges(username, page)
            }
            assert(result is RepositoryResult.Error)
        }

    @Test
    fun `Given the api returns an exception, When repository getCompletedChallenges is invoked, Then return Error`() =
        runTest {
            val username = "username"
            val page = 1
            coEvery {
                codewarsApi.getCompletedChallenges(
                    username,
                    page
                )
            } throws Exception()
            val result = repository.getCompletedChallenges(username, page)
            coVerify {
                codewarsApi.getCompletedChallenges(username, page)
            }
            assert(result is RepositoryResult.Error)
        }

    @Test
    fun `Given the api returns a challenge, When repository getChallengeDetails is invoked, Then return Success with the challenge from api`() =
        runTest {
            val challengeId = "challengeId"
            val challenge = challengeMock
            coEvery { codewarsApi.getChallengeDetails(challengeId) } returns NetworkResponse.Success(
                challengeResponseMock
            )
            val result = repository.getChallengeDetails(challengeId)
            coVerify {
                codewarsApi.getChallengeDetails(challengeId)
            }
            assert(result is RepositoryResult.Success)
            assertEquals((result as RepositoryResult.Success).data, challenge)
        }

    @Test
    fun `Given the api returns an error, When repository getChallengeDetails is invoked, Then return Error`() =
        runTest {
            val challengeId = "challengeId"
            coEvery { codewarsApi.getChallengeDetails(challengeId) } returns NetworkResponse.Error
            val result = repository.getChallengeDetails(challengeId)
            coVerify {
                codewarsApi.getChallengeDetails(challengeId)
            }
            assert(result is RepositoryResult.Error)
        }

    @Test
    fun `Given the api returns an exception, When repository getChallengeDetails is invoked, Then return Error`() =
        runTest {
            val challengeId = "challengeId"
            coEvery { codewarsApi.getChallengeDetails(challengeId) } throws Exception()
            val result = repository.getChallengeDetails(challengeId)
            coVerify {
                codewarsApi.getChallengeDetails(challengeId)
            }
            assert(result is RepositoryResult.Error)
        }
}