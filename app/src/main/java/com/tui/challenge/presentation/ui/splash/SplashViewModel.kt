package com.tui.challenge.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<SplashUiState> = MutableStateFlow(
        SplashUiState.Loading
    )
    val uiState: StateFlow<SplashUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000L)
            _uiState.update {
                SplashUiState.LoadingFinished
            }
        }
    }

}