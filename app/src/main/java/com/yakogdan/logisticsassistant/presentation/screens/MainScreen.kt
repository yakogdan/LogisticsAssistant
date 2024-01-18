package com.yakogdan.logisticsassistant.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yakogdan.logisticsassistant.navigation.MainNavGraph
import com.yakogdan.logisticsassistant.navigation.NavigationItem
import com.yakogdan.logisticsassistant.navigation.Screen
import com.yakogdan.logisticsassistant.presentation.screens.main.ChatScreen
import com.yakogdan.logisticsassistant.presentation.screens.main.ProfileScreen
import com.yakogdan.logisticsassistant.presentation.screens.main.SchedulesScreen
import com.yakogdan.logisticsassistant.presentation.screens.main.tasks.TaskInfoScreen
import com.yakogdan.logisticsassistant.presentation.screens.main.tasks.TasksScreen

@Composable
fun MainScreen(navHostController: NavHostController) {
    val items = listOf(
        NavigationItem.Tasks,
        NavigationItem.Schedules,
        NavigationItem.Chat,
        NavigationItem.Profile
    )
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ) {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    val title = stringResource(id = item.titleResId)
                    NavigationBarItem(
                        selected = currentRoute == item.screen.route,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onBackground,
                            selectedTextColor = MaterialTheme.colorScheme.onBackground,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        onClick = {
                            navHostController.navigate(item.screen.route) {
                                popUpTo(Screen.Tasks.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(text = title) },
                        icon = {
                            Icon(
                                imageVector = if (currentRoute == item.screen.route) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = title
                            )
                        }
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            MainNavGraph(
                navHostController = navHostController,
                tasksScreenContent = { TasksScreen() },
                taskInfoScreenContent = { TaskInfoScreen() },
                schedulesScreenContent = { SchedulesScreen() },
                chatScreenContent = { ChatScreen() },
                profileScreenContent = { ProfileScreen() }
            )
        }
    }
}