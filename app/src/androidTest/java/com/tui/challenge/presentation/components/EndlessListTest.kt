package com.tui.challenge.presentation.components

import androidx.compose.material.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import org.junit.Rule
import org.junit.Test

class EndlessListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenEndlessListWithTotalItems_whenScrolledToLastItem_thenLoadMoreItems() {
        var loadMoreCalled = false

        val onListBottomReached: () -> Unit = {
            loadMoreCalled = true
        }

        composeTestRule.setContent {
            val listSize = 50

            EndlessList(
                listContent = {
                    items(listSize) { index ->
                        Text(text = "Item $index")
                    }
                },
                onListBottomReached = onListBottomReached
            )
        }

        composeTestRule.onNodeWithTag(EndlessListTestTag)
            .performScrollToIndex(49)

        composeTestRule.runOnUiThread {
            assert(loadMoreCalled)
        }
    }
}