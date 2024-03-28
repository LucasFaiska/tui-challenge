package com.tui.challenge.presentation.scenes.completedchallenges

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tui.challenge.R
import com.tui.challenge.presentation.scenes.components.ChipList
import com.tui.challenge.presentation.scenes.components.EndlessList
import com.tui.challenge.presentation.scenes.components.ErrorScreen
import com.tui.challenge.presentation.scenes.components.LoadingScreen
import com.tui.challenge.presentation.theme.AppTheme
import com.tui.challenge.presentation.theme.FireBrick
import com.tui.challenge.presentation.theme.Onyx
import com.tui.challenge.presentation.theme.Timberwolf
import com.tui.challenge.presentation.theme.Typography
import com.tui.challenge.utils.completedChallengesPreviewMock
import com.tui.domain.model.CompletedChallenge

@Preview(showBackground = true)
@Composable
fun CompletedChallengesScreenSuccessPreview() {
    AppTheme {
        CompletedChallengesScreen(
            uiState = CompletedChallengesUiState.Success(
                totalItems = 3,
                challenges = completedChallengesPreviewMock,
                isLoadingMore = false
            )
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun CompletedChallengesScreenSuccessWithLoadingMorePreview() {
    AppTheme {
        CompletedChallengesScreen(
            uiState = CompletedChallengesUiState.Success(
                totalItems = 3,
                challenges = completedChallengesPreviewMock,
                isLoadingMore = true
            )
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun CompletedChallengesScreenLoadingPreview() {
    AppTheme {
        CompletedChallengesScreen(
            uiState = CompletedChallengesUiState.Loading
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun CompletedChallengesScreenErrorPreview() {
    AppTheme {
        CompletedChallengesScreen(
            uiState = CompletedChallengesUiState.Error
        ) {}
    }
}

@Composable
fun CompletedChallengesScreen(
    uiState: CompletedChallengesUiState,
    onCompletedChallengesUiEvent: (CompletedChallengesUiEvent) -> Unit
) {
    when (uiState) {
        is CompletedChallengesUiState.Loading -> LoadingScreen()
        is CompletedChallengesUiState.Success -> {
            CompletedChallengesScreenContent(
                uiState,
                onCompletedChallengesUiEvent
            )
        }

        is CompletedChallengesUiState.Error -> {
            ErrorScreen {
                onCompletedChallengesUiEvent(CompletedChallengesUiEvent.OnRetryButtonClick)
            }
        }
    }
}

@Composable
fun CompletedChallengesScreenContent(
    uiState: CompletedChallengesUiState.Success,
    onCompletedChallengesUiEvent: (CompletedChallengesUiEvent) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        CompletedChallengesScreenTitle(uiState)

        ChallengesList(
            uiState.challenges,
            onCompletedChallengesUiEvent
        )

        if (uiState.isLoadingMore) {
            BottomLoadingIndicator()
        }
    }
}

@Composable
private fun CompletedChallengesScreenTitle(uiState: CompletedChallengesUiState.Success) {
    Text(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.default_padding))
            .testTag(screenTitleTestTag),
        style = Typography.h1,
        color = Timberwolf,
        text = stringResource(
            id = R.string.completed_challenges_title,
            uiState.challenges.size,
            uiState.totalItems
        )
    )
}

@Composable
private fun ColumnScope.ChallengesList(
    completedChallengeList: List<CompletedChallenge>,
    onCompletedChallengesUiEvent: (CompletedChallengesUiEvent) -> Unit
) {
    Box(modifier = Modifier.weight(1f)) {
        EndlessList(modifier = Modifier.testTag(challengesListTestTag),
            listContent = {
                items(completedChallengeList) { challenge ->
                    CompletedChallenge(challenge, onCompletedChallengesUiEvent)
                }
            },
            onListBottomReached = {
                onCompletedChallengesUiEvent(
                    CompletedChallengesUiEvent.OnListScrolledToBottom(
                        completedChallengeList.size / 200
                    )
                )
            }
        )
    }
}

@Composable
private fun ColumnScope.BottomLoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(70.dp)
            .padding(dimensionResource(id = R.dimen.default_padding))
            .align(Alignment.CenterHorizontally),
        color = FireBrick
    )
}

@Composable
private fun CompletedChallenge(
    challenge: CompletedChallenge,
    onCompletedChallengesUiEvent: (CompletedChallengesUiEvent) -> Unit
) {
    Box(modifier = Modifier
        .background(color = Onyx)
        .fillMaxWidth()
        .testTag(challengeItemTestTag)
        .clickable {
            onCompletedChallengesUiEvent(
                CompletedChallengesUiEvent.OnChallengeClick(
                    challenge.id
                )
            )
        }
    ) {

        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.default_padding)),
        ) {

            Text(
                style = Typography.body1,
                fontWeight = FontWeight.Bold,
                text = challenge.name.orEmpty(),
                color = Timberwolf
            )

            Text(
                style = Typography.body1, text = stringResource(
                    id = R.string.completed_label, challenge.completedAt
                ), color = Timberwolf
            )

            challenge.completedLanguages?.let {
                ChipList(it)
            }
        }

        Divider(thickness = 1.dp, color = Black)
    }
}

const val screenTitleTestTag = "screenTitleTestTag"
const val challengesListTestTag = "challengesListTestTag"
const val challengeItemTestTag = "challengeItemTestTag"