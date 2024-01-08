package com.yakogdan.logisticsassistant.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun LoginNavGraph(
    navHostController: NavHostController,
    splashScreenContent: @Composable () -> Unit,
    loginScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            splashScreenContent()
        }
        composable(Screen.Login.route) {
            loginScreenContent()
        }
    }
}