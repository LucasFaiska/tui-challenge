package com.tui.challenge.presentation.scenes.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tui.challenge.navigation.NavTarget
import com.tui.challenge.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState: MutableStateFlow<SplashUiState> = MutableStateFlow(
        SplashUiState.Loading
    )
    val uiState: StateFlow<SplashUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000L)
            navigator.navigateTo(NavTarget.CompletedChallenges)
        }
    }

}