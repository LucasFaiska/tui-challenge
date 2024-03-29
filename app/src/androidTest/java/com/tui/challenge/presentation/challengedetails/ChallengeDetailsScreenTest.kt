package com.tui.challenge.presentation.challengedetails

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.tui.challenge.presentation.components.ErrorScreenTestTag
import com.tui.challenge.presentation.components.LoadingScreenTestTag
import com.tui.challenge.presentation.scenes.challengedetails.ChallengeDetailsScreen
import com.tui.challenge.presentation.scenes.challengedetails.ChallengeDetailsUiState
import com.tui.challenge.utils.challengeDetailsMock
import org.junit.Rule
import org.junit.Test

class ChallengeDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context =
        InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun givenChallengeDetailsScreenUiStateSuccess_whenRendered_thenVerifyElements() {
        val uiState = ChallengeDetailsUiState.Success(
            challenge = challengeDetailsMock
        )

        composeTestRule.setContent {
            ChallengeDetailsScreen(
                uiState = uiState
            ) {}
        }

        composeTestRule.onNodeWithText(challengeDetailsMock.name.orEmpty())
            .assertIsDisplayed()
    }

    @Test
    fun givenChallengeDetailsScreenUiStateError_whenRendered_thenVerifyElements() {
        val uiState = ChallengeDetailsUiState.Error(
            challengeId = challengeDetailsMock.id.orEmpty()
        )

        composeTestRule.setContent {
            ChallengeDetailsScreen(
                uiState = uiState
            ) {}
        }

        composeTestRule.onNodeWithTag(ErrorScreenTestTag).assertIsDisplayed()
    }

    @Test
    fun givenChallengeDetailsScreenUiStateLoading_whenRendered_thenVerifyElements() {
        val uiState = ChallengeDetailsUiState.Loading

        composeTestRule.setContent {
            ChallengeDetailsScreen(
                uiState = uiState
            ) {}
        }

        composeTestRule.onNodeWithTag(LoadingScreenTestTag).assertIsDisplayed()
    }

}