package com.yakogdan.logisticsassistant.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    companion object {
        const val TASKS = "Задания"
        const val SCHEDULES = "Графики"
        const val CHAT = "Чат"
        const val PROFILE = "Профиль"
    }
}