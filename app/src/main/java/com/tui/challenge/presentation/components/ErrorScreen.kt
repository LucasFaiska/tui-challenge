package com.tui.challenge.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tui.challenge.R
import com.tui.challenge.presentation.theme.FireBrick
import com.tui.challenge.presentation.theme.Timberwolf
import com.tui.challenge.presentation.theme.Typography

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen {}
}

@Composable
fun ErrorScreen(onRetryButtonClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag(ErrorScreenTestTag),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                style = Typography.h1,
                color = Timberwolf,
                text = stringResource(id = R.string.error_screen_error_message)
            )

            Button(
                modifier = Modifier
                    .width(140.dp)
                    .padding(dimensionResource(id = R.dimen.default_padding)),
                onClick = { onRetryButtonClick() },
                colors = ButtonDefaults.buttonColors(backgroundColor = FireBrick)
            ) {
                Text(
                    text = stringResource(id = R.string.error_screen_button_label),
                    color = Color.White
                )
            }
        }
    }
}

const val ErrorScreenTestTag = "ErrorScreenTestTag"