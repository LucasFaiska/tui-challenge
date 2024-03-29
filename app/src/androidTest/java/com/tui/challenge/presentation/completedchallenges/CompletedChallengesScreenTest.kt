package com.tui.challenge.presentation.completedchallenges

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.tui.challenge.R
import com.tui.challenge.presentation.components.ErrorScreenTestTag
import com.tui.challenge.presentation.components.LoadingScreenTestTag
import com.tui.challenge.presentation.scenes.completedchallenges.CompletedChallengesScreen
import com.tui.challenge.presentation.scenes.completedchallenges.CompletedChallengesUiEvent
import com.tui.challenge.presentation.scenes.completedchallenges.CompletedChallengesUiState
import com.tui.challenge.presentation.scenes.completedchallenges.bottomLoadingIndicatorTestTag
import com.tui.challenge.presentation.scenes.completedchallenges.challengesListTestTag
import com.tui.challenge.presentation.scenes.completedchallenges.screenTitleTestTag
import com.tui.challenge.presentation.scenes.completedchallenges.successScreenTesTag
import com.tui.challenge.utils.completedChallengesMock
import org.junit.Rule
import org.junit.Test

class CompletedChallengesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun givenCompletedChallengesScreenUiStateSuccess_with_isLoadingMore_false_whenRendered_thenVerifyElements() {
        val uiState = CompletedChallengesUiState.Success(
            isLoadingMore = false,
            challenges = completedChallengesMock,
            totalItems = 50
        )

        composeTestRule.setContent {
            CompletedChallengesScreen(uiState = uiState) {}
        }

        val expectedTitle = context.getString(
            R.string.completed_challenges_title,
            completedChallengesMock.size,
            uiState.totalItems
        )

        composeTestRule.onNodeWithTag(successScreenTesTag).assertExists()
        composeTestRule.onNodeWithTag(successScreenTesTag).assertIsDisplayed()
        composeTestRule.onNodeWithTag(screenTitleTestTag)
            .assertTextContains(expectedTitle)
        composeTestRule.onNodeWithTag(challengesListTestTag).assertIsDisplayed()

        for (challenge in completedChallengesMock) {
            composeTestRule.onNodeWithText(challenge.name.orEmpty())
                .assertIsDisplayed()
        }
    }

    @Test
    fun givenCompletedChallengesScreenUiStateSuccess_with_isLoadingMore_true_whenRendered_thenVerifyElements() {
        val uiState = CompletedChallengesUiState.Success(
            isLoadingMore = true,
            challenges = completedChallengesMock,
            totalItems = 50
        )

        composeTestRule.setContent {
            CompletedChallengesScreen(uiState = uiState) {}
        }

        composeTestRule.onNodeWithTag(bottomLoadingIndicatorTestTag)
            .assertIsDisplayed()
    }

    @Test
    fun givenCompletedChallengesScreenUiStateLoading_whenRendered_thenVerifyElements() {
        val uiState = CompletedChallengesUiState.Loading

        composeTestRule.setContent {
            CompletedChallengesScreen(uiState = uiState) {}
        }

        composeTestRule.onNodeWithTag(LoadingScreenTestTag).assertExists()
    }

    @Test
    fun givenCompletedChallengesScreenUiStateError_whenRendered_thenVerifyElements() {
        val uiState = CompletedChallengesUiState.Error
        var retryActionInvoked = false

        val onRetryAction: (CompletedChallengesUiEvent) -> Unit = {
            retryActionInvoked = it is CompletedChallengesUiEvent.OnRetryButtonClick
        }

        composeTestRule.setContent {
            CompletedChallengesScreen(uiState = uiState) {
                onRetryAction(it)
            }
        }

        composeTestRule.onNodeWithTag(ErrorScreenTestTag).assertExists()

        composeTestRule.onNodeWithText("Retry").performClick()

        composeTestRule.runOnUiThread {
            assert(retryActionInvoked)
        }
    }
}