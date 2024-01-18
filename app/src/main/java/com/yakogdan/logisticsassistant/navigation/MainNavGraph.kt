package com.yakogdan.logisticsassistant.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    tasksScreenContent: @Composable () -> Unit,
    taskInfoScreenContent: @Composable () -> Unit,
    schedulesScreenContent: @Composable () -> Unit,
    chatScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Tasks.route
    ) {
        composable(Screen.Tasks.route) {
            tasksScreenContent()
        }
        composable(Screen.TaskInfo.route) {
            taskInfoScreenContent()
        }
        composable(Screen.Schedules.route) {
            schedulesScreenContent()
        }
        composable(Screen.Chat.route) {
            chatScreenContent()
        }
        composable(Screen.Profile.route) {
            profileScreenContent()
        }
    }
}