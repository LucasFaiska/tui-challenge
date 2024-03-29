package com.tui.challenge.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class CoroutineTestRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher(
        TestCoroutineScheduler()
    ),
) : TestWatcher() {

    val scope = TestScope(dispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}