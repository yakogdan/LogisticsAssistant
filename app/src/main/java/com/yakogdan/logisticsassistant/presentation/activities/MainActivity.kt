package com.yakogdan.logisticsassistant.presentation.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.yakogdan.logisticsassistant.navigation.LoginNavGraph
import com.yakogdan.logisticsassistant.navigation.rememberNavigationState
import com.yakogdan.logisticsassistant.presentation.screens.LoginScreen
import com.yakogdan.logisticsassistant.presentation.screens.MainScreen
import com.yakogdan.logisticsassistant.presentation.screens.SplashScreen
import com.yakogdan.logisticsassistant.presentation.ui.theme.LogisticsAssistantTheme

class MainActivity : ComponentActivity() {

    private var authStateSharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authStateSharedPref = getSharedPreferences("authState", MODE_PRIVATE)
        putAuthState(true)
        val authorized = authStateSharedPref?.getBoolean("authorized", false) ?: false
        setContent {
            val navigationState = rememberNavigationState()
            LogisticsAssistantTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    if (authorized) {
                        MainScreen(navigationState.navHostController)
                    } else {
                        LoginNavGraph(
                            navHostController = navigationState.navHostController,
                            splashScreenContent = { SplashScreen(navigationState.navHostController) },
                            loginScreenContent = { LoginScreen() }
                        )
                    }
                }
            }
        }
    }

    private fun putAuthState(authorized: Boolean) {
        val editor = authStateSharedPref?.edit()
        editor?.putBoolean("authorized", authorized)
        editor?.apply()
    }
}