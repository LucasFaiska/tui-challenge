package com.tui.challenge.presentation.scenes.splash

import app.cash.turbine.test
import com.tui.challenge.navigation.NavTarget
import com.tui.challenge.navigation.Navigator
import com.tui.challenge.util.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: SplashViewModel

    @MockK(relaxUnitFun = true)
    private lateinit var navigator: Navigator

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = SplashViewModel(navigator)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Given SplashViewModel is created, Then initial uiState is Loading`() =
        runTest {
            viewModel.uiState.test {
                assertEquals(SplashUiState.Loading, awaitItem())
            }
        }

    @Test
    fun `Given SplashViewModel is created, When delay is completed, Then navigateTo CompletedChallenges is called`() =
        runTest {
            viewModel.uiState.test {
                assertEquals(SplashUiState.Loading, awaitItem())
                coVerify(timeout = 4000, atLeast = 1) {
                    navigator.navigateTo(NavTarget.CompletedChallenges)
                }
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given SplashViewModel is created, When delay is in progress, Then uiState remains Loading`() =
        runTest {
            viewModel.uiState.test {
                advanceTimeBy(2000L)
                assertEquals(SplashUiState.Loading, awaitItem())
            }
        }

}