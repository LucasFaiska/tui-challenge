package com.tui.challenge.presentation.scenes.completedchallenges

import app.cash.turbine.test
import com.tui.challenge.navigation.NavTarget
import com.tui.challenge.navigation.Navigator
import com.tui.challenge.presentation.scenes.completedchallenges.CompletedChallengesViewModel.Companion.USER
import com.tui.challenge.util.CoroutineTestRule
import com.tui.challenge.utils.completedChallengesMock
import com.tui.domain.model.PageListData
import com.tui.domain.usecase.completedchallenges.GetCompletedChallengesFromUser
import com.tui.domain.usecase.completedchallenges.GetCompletedChallengesFromUserResult
import io.mockk.MockKAnnotations
import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CompletedChallengesViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: CompletedChallengesViewModel

    @MockK(relaxUnitFun = true)
    private lateinit var getCompletedChallengesFromUser: GetCompletedChallengesFromUser

    @MockK(relaxUnitFun = true)
    private lateinit var navigator: Navigator

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Given GetCompletedChallengesFromUser results in Success, When init viewModel, Then returns the correct Ui State`() =
        runTest {
            val expectedUiState = CompletedChallengesUiState.Success(
                totalItems = 100,
                challenges = completedChallengesMock,
                isLoadingMore = false
            )

            val useCaseResult = GetCompletedChallengesFromUserResult.Success(
                PageListData(
                    items = completedChallengesMock,
                    totalItems = 100,
                    totalPages = 1,
                )
            )

            coEvery {
                getCompletedChallengesFromUser(USER, 0)
            } returns useCaseResult

            viewModel = CompletedChallengesViewModel(
                getCompletedChallengesFromUser,
                navigator
            )

            viewModel.uiState.test {
                assertEquals(expectedUiState, awaitItem())
            }

            coVerify { getCompletedChallengesFromUser(USER, 0) }
        }

    @Test
    fun `Given GetCompletedChallengesFromUser results in Error, When init viewModel, Then returns the correct Ui State`() =
        runTest {
            val expectedUiState = CompletedChallengesUiState.Error
            val useCaseResult = GetCompletedChallengesFromUserResult.Error

            coEvery {
                getCompletedChallengesFromUser(USER, 0)
            } returns useCaseResult

            viewModel = CompletedChallengesViewModel(
                getCompletedChallengesFromUser,
                navigator
            )

            viewModel.uiState.test {
                assertEquals(expectedUiState, awaitItem())
            }

            coVerify { getCompletedChallengesFromUser(USER, 0) }
        }

    @Test
    fun `Given GetCompletedChallengesFromUser results in Success, When onEvent is called with OnListScrolledToBottom, Then returns the correct sequence of Ui States`() =
        runTest {
            val useCaseResult = GetCompletedChallengesFromUserResult.Success(
                PageListData(
                    items = completedChallengesMock,
                    totalItems = 100,
                    totalPages = 1,
                )
            )

            coEvery {
                getCompletedChallengesFromUser(USER, 0)
            } returns useCaseResult

            coEvery {
                getCompletedChallengesFromUser(USER, 1)
            } returns useCaseResult

            viewModel = CompletedChallengesViewModel(
                getCompletedChallengesFromUser,
                navigator
            )

            viewModel.uiState.test {
                var uiState = awaitItem() as CompletedChallengesUiState.Success
                assertFalse(uiState.isLoadingMore)
                viewModel.onEvent(
                    CompletedChallengesUiEvent.OnListScrolledToBottom(
                        1
                    )
                )
                uiState = awaitItem() as CompletedChallengesUiState.Success
                assertTrue(uiState.isLoadingMore)
                uiState = awaitItem() as CompletedChallengesUiState.Success
                assertFalse(uiState.isLoadingMore)
                assertEquals(
                    completedChallengesMock + completedChallengesMock,
                    uiState.challenges
                )
            }

            coVerify(Ordering.SEQUENCE) {
                getCompletedChallengesFromUser(USER, 0)
                getCompletedChallengesFromUser(USER, 1)
            }
        }

    @Test
    fun `Given GetCompletedChallengesFromUser results in Success, When onEvent is called with OnRetryButtonClick, Then returns the correct Ui State`() =
        runTest {
            val useCaseResult = GetCompletedChallengesFromUserResult.Success(
                PageListData(
                    items = completedChallengesMock,
                    totalItems = 100,
                    totalPages = 1,
                )
            )

            coEvery {
                getCompletedChallengesFromUser(USER, 0)
            } returnsMany listOf(
                GetCompletedChallengesFromUserResult.Error,
                useCaseResult
            )

            viewModel = CompletedChallengesViewModel(
                getCompletedChallengesFromUser,
                navigator
            )

            viewModel.uiState.test {
                var uiState = awaitItem()
                assertEquals(CompletedChallengesUiState.Error, uiState)
                viewModel.onEvent(CompletedChallengesUiEvent.OnRetryButtonClick)
                uiState = awaitItem() as CompletedChallengesUiState.Success
                assertFalse(uiState.isLoadingMore)
                assertEquals(
                    completedChallengesMock,
                    uiState.challenges
                )
            }

            coVerify(exactly = 2) {
                getCompletedChallengesFromUser(USER, 0)
            }
        }

    @Test
    fun `When onEvent is called with OnChallengeClick, Then navigate to ChallengeDetails is called`() =
        runTest {
            val challengeId = "challengeId"

            coEvery {
                getCompletedChallengesFromUser(USER, 0)
            } returns GetCompletedChallengesFromUserResult.Success(
                PageListData(
                    items = completedChallengesMock,
                    totalItems = 100,
                    totalPages = 1,
                )
            )

            viewModel = CompletedChallengesViewModel(
                getCompletedChallengesFromUser,
                navigator
            )

            viewModel.onEvent(
                CompletedChallengesUiEvent.OnChallengeClick(challengeId)
            )

            coVerify {
                navigator.navigateTo(NavTarget.ChallengeDetails(challengeId))
            }
        }
}