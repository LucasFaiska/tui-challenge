package com.tui.challenge.presentation.splash

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import com.tui.challenge.presentation.scenes.splash.SplashScreen
import com.tui.challenge.presentation.scenes.splash.SplashScreenTestTag
import com.tui.challenge.presentation.scenes.splash.SplashUiState
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenSplashScreenContent_whenRendered_thenVerifyElements() {
        val uiState = SplashUiState.Loading

        composeTestRule.setContent {
            SplashScreen(uiState)
        }

        composeTestRule.onNodeWithTag(SplashScreenTestTag)
            .onChild()
            .assertExists()

        composeTestRule.onNodeWithTag(SplashScreenTestTag)
            .onChild()
            .assertIsDisplayed()
    }

}