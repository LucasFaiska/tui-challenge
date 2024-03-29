package com.tui.challenge.presentation.components

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChipListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenListOfStrings_WhenRendered_ThenCorrectNumberOfChipsIsDisplayed() {
        val chips = listOf("Chip 1", "Chip 2", "Chip 3")

        composeTestRule.setContent {
            ChipList(chips = chips)
        }

        composeTestRule.onNodeWithTag(ChipListTestTag).onChildren()
            .assertCountEquals(chips.size)

        chips.forEach { chip ->
            composeTestRule.onNodeWithText(chip).assertExists()
        }
    }

    @Test
    fun givenEmptyStringInList_WhenRendered_ThenEmptyStringIsNotDisplayed() {
        val chips = listOf("Chip 1", "", "Chip 3")

        composeTestRule.setContent {
            ChipList(chips = chips)
        }

        composeTestRule.onNodeWithTag(ChipListTestTag).onChildren()
            .assertCountEquals(2)

        chips.filter { it.isNotEmpty() }.forEach { chip ->
            composeTestRule.onNodeWithText(chip).assertExists()
        }
    }

}