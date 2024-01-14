package com.yakogdan.logisticsassistant.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.yakogdan.logisticsassistant.navigation.BottomNavItem
import com.yakogdan.logisticsassistant.navigation.BottomNavItem.Companion.CHAT
import com.yakogdan.logisticsassistant.navigation.BottomNavItem.Companion.PROFILE
import com.yakogdan.logisticsassistant.navigation.BottomNavItem.Companion.SCHEDULES
import com.yakogdan.logisticsassistant.navigation.BottomNavItem.Companion.TASKS
import com.yakogdan.logisticsassistant.navigation.MainNavGraph
import com.yakogdan.logisticsassistant.navigation.Screen
import com.yakogdan.logisticsassistant.presentation.screens.main.ChatScreen
import com.yakogdan.logisticsassistant.presentation.screens.main.ProfileScreen
import com.yakogdan.logisticsassistant.presentation.screens.main.SchedulesScreen
import com.yakogdan.logisticsassistant.presentation.screens.main.TasksScreen

@Composable
fun MainScreen(navHostController: NavHostController) {
    val items = listOf(
        BottomNavItem(
            title = TASKS,
            selectedIcon = Icons.Filled.CheckCircle,
            unselectedIcon = Icons.Outlined.CheckCircle
        ),
        BottomNavItem(
            title = SCHEDULES,
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange
        ),
        BottomNavItem(
            title = CHAT,
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email
        ),
        BottomNavItem(
            title = PROFILE,
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        ),
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onBackground,
                            selectedTextColor = MaterialTheme.colorScheme.onBackground,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        onClick = {
                            selectedItemIndex = index
                            val route: String = when (item.title) {
                                TASKS -> {
                                    Screen.Tasks.route
                                }

                                SCHEDULES -> {
                                    Screen.Schedules.route
                                }

                                CHAT -> {
                                    Screen.Chat.route
                                }

                                PROFILE -> {
                                    Screen.Profile.route
                                }

                                else -> {
                                    throw RuntimeException("Неизвестный экран")
                                }
                            }
                            navHostController.navigate(route)
                        },
                        label = { Text(text = item.title) },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.title
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
                schedulesScreenContent = { SchedulesScreen() },
                chatScreenContent = { ChatScreen() },
                profileScreenContent = { ProfileScreen() }
            )
        }
    }
}