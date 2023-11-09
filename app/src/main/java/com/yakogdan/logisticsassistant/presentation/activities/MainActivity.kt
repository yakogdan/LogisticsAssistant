package com.yakogdan.logisticsassistant.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.yakogdan.logisticsassistant.presentation.ui.theme.LogisticsAssistantTheme
import com.yakogdan.logisticsassistant.presentation.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            LogisticsAssistantTheme {
                Text(text = "Hello World!", fontSize = 20.sp)
            }
        }
    }
}