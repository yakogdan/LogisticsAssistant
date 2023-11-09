package com.yakogdan.logisticsassistant.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.yakogdan.logisticsassistant.navigation.AppNavGraph
import com.yakogdan.logisticsassistant.navigation.rememberNavigationState
import com.yakogdan.logisticsassistant.presentation.screens.LoginScreen
import com.yakogdan.logisticsassistant.presentation.screens.SplashScreen
import com.yakogdan.logisticsassistant.presentation.ui.theme.LogisticsAssistantTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogisticsAssistantTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navigationState = rememberNavigationState()
                    AppNavGraph(
                        navHostController = navigationState.navHostController,
                        splashScreenContent = { SplashScreen(navigationState.navHostController) },
                        loginScreenContent = { LoginScreen() }
                    )
                }
            }
        }
    }
}