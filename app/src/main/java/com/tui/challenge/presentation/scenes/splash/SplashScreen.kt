package com.tui.challenge.presentation.scenes.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.tui.challenge.R
import com.tui.challenge.navigation.NavigationActions
import com.tui.challenge.presentation.theme.Onyx

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(SplashUiState.Loading)
}

@Composable
fun SplashScreen(uiState: SplashUiState) {

    Scaffold { paddingValues ->
        when (uiState) {
            is SplashUiState.Loading -> SplashScreenContent(paddingValues)
        }
    }

}

@Composable
fun SplashScreenContent(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .background(color = Onyx)
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.padding(48.dp),
            painter = painterResource(R.drawable.dark_text_logo_vertical),
            contentDescription = SPLASH_SCREEN_LOGO_CONTENT_DESCRIPTION
        )
    }
}

const val SPLASH_SCREEN_LOGO_CONTENT_DESCRIPTION = "Splash Logo"