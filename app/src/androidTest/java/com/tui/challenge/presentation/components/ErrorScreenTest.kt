package com.tui.challenge.presentation.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ErrorScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenErrorScreen_whenRetryButtonClicked_thenRetryActionIsInvoked() {
        var retryActionInvoked = false

        val onRetryAction: () -> Unit = {
            retryActionInvoked = true
        }

        composeTestRule.setContent {
            ErrorScreen {
                onRetryAction()
            }
        }

        composeTestRule.onNodeWithText("Retry")
            .performClick()

        composeTestRule.runOnUiThread {
            assert(retryActionInvoked)
        }
    }

}