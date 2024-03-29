package com.tui.challenge.presentation.scenes.completedchallenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tui.challenge.navigation.NavTarget
import com.tui.challenge.navigation.Navigator
import com.tui.domain.usecase.completedchallenges.GetCompletedChallengesFromUser
import com.tui.domain.usecase.completedchallenges.GetCompletedChallengesFromUserResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompletedChallengesViewModel @Inject constructor(
    val getCompletedChallengesFromUser: GetCompletedChallengesFromUser,
    val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow<CompletedChallengesUiState>(
        CompletedChallengesUiState.Loading
    )
    val uiState: StateFlow<CompletedChallengesUiState> = _uiState

    init {
        loadCompletedChallenges(0)
    }

    private fun loadCompletedChallenges(currentPage: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            handleResult(getCompletedChallengesFromUser(USER, currentPage))
        }
    }

    private fun handleResult(result: GetCompletedChallengesFromUserResult) {
        when (result) {
            is GetCompletedChallengesFromUserResult.Success -> {
                _uiState.update {
                    getUpdatedChallengeList(it, result)
                }
            }

            else -> {
                _uiState.value = CompletedChallengesUiState.Error
            }
        }
    }

    private fun getUpdatedChallengeList(
        currentUiState: CompletedChallengesUiState,
        result: GetCompletedChallengesFromUserResult.Success
    ): CompletedChallengesUiState.Success {
        return if (currentUiState is CompletedChallengesUiState.Success) {
            currentUiState.copy(
                challenges = currentUiState.challenges.plus(
                    result.data.items ?: emptyList()
                ),
                isLoadingMore = false
            )
        } else {
            CompletedChallengesUiState.Success(
                totalItems = result.data.totalItems,
                challenges = result.data.items ?: emptyList(),
                isLoadingMore = false
            )
        }
    }

    fun onEvent(event: CompletedChallengesUiEvent) {
        when (event) {
            is CompletedChallengesUiEvent.OnListScrolledToBottom -> {
                _uiState.update {
                    getLoadingMoreState(it)
                }
                loadCompletedChallenges(event.currentPage)
            }

            is CompletedChallengesUiEvent.OnRetryButtonClick -> {
                loadCompletedChallenges(0)
            }

            is CompletedChallengesUiEvent.OnChallengeClick -> {
                viewModelScope.launch {
                    navigator.navigateTo(NavTarget.ChallengeDetails(event.challengeId))
                }
            }
        }
    }

    private fun getLoadingMoreState(currentUiState: CompletedChallengesUiState): CompletedChallengesUiState {
        return if (currentUiState is CompletedChallengesUiState.Success) {
            currentUiState.copy(isLoadingMore = true)
        } else {
            currentUiState
        }
    }

    companion object {
        const val USER = "g964"
    }

}