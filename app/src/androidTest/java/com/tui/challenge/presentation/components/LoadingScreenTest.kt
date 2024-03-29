package com.tui.challenge.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class LoadingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenLoadingScreen_whenRendered_thenRenderCorrectly() {
        composeTestRule.setContent {
            LoadingScreen()
        }

        composeTestRule.onNodeWithTag(LoadingScreenTestTag).assertExists()
        composeTestRule.onNodeWithTag(LoadingScreenTestTag).assertIsDisplayed()
    }
}