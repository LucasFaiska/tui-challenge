package com.tui.challenge.presentation.scenes.challengedetails

import app.cash.turbine.test
import com.tui.challenge.util.CoroutineTestRule
import com.tui.challenge.utils.challengeDetailsMock
import com.tui.domain.usecase.challengedetails.GetChallengeDetails
import com.tui.domain.usecase.challengedetails.GetChallengeDetailsResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChallengeDetailsViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: ChallengeDetailsViewModel

    @MockK
    private lateinit var getChallengeDetails: GetChallengeDetails

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = ChallengeDetailsViewModel(getChallengeDetails)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Given GetChallengeDetails results in Success, When loadChallengeDetails is called, Then returns the correct Ui State`() =
        runTest {
            val expectedUiState = ChallengeDetailsUiState.Success(
                challengeDetailsMock
            )

            val useCaseResult = GetChallengeDetailsResult.Success(
                challengeDetailsMock
            )

            coEvery {
                getChallengeDetails("challenge-id")
            } returns useCaseResult

            viewModel.loadChallengeDetails("challenge-id")

            viewModel.uiState.test {
                val uiState = awaitItem() as ChallengeDetailsUiState.Success
                assertEquals(expectedUiState.challenge, uiState.challenge)
            }

            coVerify {
                getChallengeDetails("challenge-id")
            }
        }

    @Test
    fun `Given GetChallengeDetails results in Error, When loadChallengeDetails is called, Then returns the correct Ui State`() =
        runTest {
            val expectedUiState = ChallengeDetailsUiState.Error
            val useCaseResult = GetChallengeDetailsResult.Error

            coEvery {
                getChallengeDetails("challenge-id")
            } returns useCaseResult

            viewModel.loadChallengeDetails("challenge-id")

            viewModel.uiState.test {
                val uiState = awaitItem() as ChallengeDetailsUiState.Error
                assertEquals(expectedUiState, uiState)
            }

            coVerify {
                getChallengeDetails("challenge-id")
            }
        }

    @Test
    fun `Given GetChallengeDetails results in Success, When onEvent is called with OnRetryButtonClick, Then returns the correct Ui State`() =
        runTest {
            val useCaseResult = GetChallengeDetailsResult.Success(
                challengeDetailsMock
            )

            coEvery {
                getChallengeDetails("challenge-id")
            } returnsMany listOf(
                GetChallengeDetailsResult.Error,
                useCaseResult
            )

            viewModel.loadChallengeDetails("challenge-id")

            viewModel.uiState.test {
                var uiState = awaitItem()
                assertEquals(ChallengeDetailsUiState.Error, uiState)
                viewModel.onEvent(ChallengeDetailsUiEvent.OnRetryButtonClick("challenge-id"))
                uiState = awaitItem() as ChallengeDetailsUiState.Success
                assertEquals(challengeDetailsMock, uiState.challenge)
            }

            coVerify(exactly = 2) {
                getChallengeDetails("challenge-id")
            }
        }

}