package com.tui.challenge.di

import com.tui.challenge.navigation.NavigatorImpl
import org.junit.Test

class NavigationModuleTest {

    @Test
    fun `Given a NavigationModule, When provideNavigator, Then returns the expected Navigator`() {
        val providedNavigator = NavigationModule.provideNavigator()
        assert(providedNavigator is NavigatorImpl)
    }
}