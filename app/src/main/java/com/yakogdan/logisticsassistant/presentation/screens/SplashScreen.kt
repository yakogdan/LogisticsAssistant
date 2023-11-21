package com.yakogdan.logisticsassistant.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.yakogdan.logisticsassistant.R
import com.yakogdan.logisticsassistant.navigation.Screen
import com.yakogdan.logisticsassistant.navigation.rememberNavigationState
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(500)
        navHostController.navigate(Screen.Login.route) {
            popUpTo(0)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = stringResource(
                R.string.splash_screen_description
            )
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(navHostController = rememberNavigationState().navHostController)
}