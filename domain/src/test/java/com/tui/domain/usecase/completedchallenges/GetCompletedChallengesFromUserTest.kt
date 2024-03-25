package com.tui.domain.usecase.completedchallenges

import com.tui.domain.repository.ChallengeRepository
import com.tui.domain.repository.RepositoryResult
import com.tui.domain.utils.Mocks.Companion.completedChallengesMock
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCompletedChallengesFromUserTest {

    private lateinit var useCase: GetCompletedChallengesFromUser

    @MockK
    private lateinit var repository: ChallengeRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetCompletedChallengesFromUserImpl(repository)
    }

    @Test
    fun `Given the username is empty, when the use case is invoked, then return UsernameEmpty`() = runBlocking {
        val result = useCase.execute("", 1)
        assert(result is GetCompletedChallengesFromUserResult.UsernameEmpty)
    }

    @Test
    fun `Given the repository returns a list of completed challenges, When the use case is invoked, Then return Success with the list of completed challenges from repository`() =
        runBlocking {
            val username = "username"
            val page = 1
            val completedChallenges = completedChallengesMock
            coEvery { repository.getCompletedChallenges(username, page) } returns RepositoryResult.Success(
                completedChallenges
            )
            val result = useCase.execute(username, page)
            assert(result is GetCompletedChallengesFromUserResult.Success)
            assert((result as GetCompletedChallengesFromUserResult.Success).data == completedChallenges)
        }

    @Test
    fun `Given the repository returns an error, When the use case is invoked, Then return Error`() = runBlocking {
        val username = "username"
        val page = 1
        coEvery { repository.getCompletedChallenges(username, page) } returns RepositoryResult.Error
        val result = useCase.execute(username, page)
        assert(result is GetCompletedChallengesFromUserResult.Error)
    }

}