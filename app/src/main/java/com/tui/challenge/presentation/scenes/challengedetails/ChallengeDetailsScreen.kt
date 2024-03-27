package com.tui.challenge.presentation.scenes.challengedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tui.challenge.R
import com.tui.challenge.presentation.scenes.components.ChipList
import com.tui.challenge.presentation.scenes.components.ErrorScreen
import com.tui.challenge.presentation.scenes.components.LoadingScreen
import com.tui.challenge.presentation.theme.AppTheme
import com.tui.challenge.presentation.theme.Timberwolf
import com.tui.challenge.presentation.theme.Typography
import com.tui.challenge.utils.challengeDetailsPreviewMock
import com.tui.domain.model.Challenge
import dev.jeziellago.compose.markdowntext.MarkdownText

@Preview(showBackground = true)
@Composable
fun ChallengeDetailsScreenSuccessPreview() {
    AppTheme {
        ChallengeDetailsScreen(
            challengeId = challengeDetailsPreviewMock.id.orEmpty(),
            uiState = ChallengeDetailsUiState.Success(
                challengeDetailsPreviewMock
            )
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengeDetailsScreenLoadingPreview() {
    AppTheme {
        ChallengeDetailsScreen(
            challengeId = challengeDetailsPreviewMock.id.orEmpty(),
            uiState = ChallengeDetailsUiState.Loading
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengeDetailsScreenErrorPreview() {
    AppTheme {
        ChallengeDetailsScreen(
            challengeId = challengeDetailsPreviewMock.id.orEmpty(),
            uiState = ChallengeDetailsUiState.Error
        ) {}
    }
}

@Composable
fun ChallengeDetailsScreen(
    challengeId: String,
    uiState: ChallengeDetailsUiState,
    onChallengeDetailsUiEvent: (ChallengeDetailsUiEvent) -> Unit
) {
    when (uiState) {
        is ChallengeDetailsUiState.Loading -> LoadingScreen()
        is ChallengeDetailsUiState.Success -> {
            ChallengeDetailsScreenContent(uiState.challenge)
        }

        is ChallengeDetailsUiState.Error -> {
            ErrorScreen {
                onChallengeDetailsUiEvent(
                    ChallengeDetailsUiEvent.OnRetryButtonClick(
                        challengeId
                    )
                )
            }
        }
    }
}

@Composable
fun ChallengeDetailsScreenContent(challenge: Challenge) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.default_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding))
    ) {
        Text(
            style = Typography.h1,
            color = Timberwolf,
            text = challenge.name.orEmpty()
        )

        MarkdownText(
            markdown = challenge.description.orEmpty(),
            style = Typography.body1.merge(color = Timberwolf)
        )

        Text(
            style = Typography.caption,
            color = Timberwolf,
            text = stringResource(
                id = R.string.created_by_label,
                challenge.createdBy?.username.orEmpty()
            )
        )

        Text(
            style = Typography.caption,
            color = Timberwolf,
            text = stringResource(
                id = R.string.approved_by_label,
                challenge.approvedBy?.username.orEmpty()
            )
        )

        ChipList(chips = challenge.tags.orEmpty())
    }
}