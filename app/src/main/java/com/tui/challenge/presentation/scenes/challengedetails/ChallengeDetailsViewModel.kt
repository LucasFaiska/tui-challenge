package com.tui.challenge.presentation.scenes.challengedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tui.domain.usecase.challengedetails.GetChallengeDetails
import com.tui.domain.usecase.challengedetails.GetChallengeDetailsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeDetailsViewModel @Inject constructor(
    val getChallengeDetails: GetChallengeDetails
) : ViewModel() {

    private val _uiState = MutableStateFlow<ChallengeDetailsUiState>(
        ChallengeDetailsUiState.Loading
    )
    val uiState: StateFlow<ChallengeDetailsUiState> = _uiState

    fun loadChallengeDetails(id: String) {
        viewModelScope.launch {
            handleResult(id, getChallengeDetails(id))
        }
    }

    private fun handleResult(id: String, result: GetChallengeDetailsResult) {
        when (result) {
            is GetChallengeDetailsResult.Success -> {
                _uiState.value = ChallengeDetailsUiState.Success(result.data)
            }

            else -> {
                _uiState.value = ChallengeDetailsUiState.Error(id)
            }
        }
    }

    fun onEvent(event: ChallengeDetailsUiEvent) {
        when (event) {
            is ChallengeDetailsUiEvent.OnRetryButtonClick -> {
                loadChallengeDetails(event.challengeId)
            }
        }
    }

}