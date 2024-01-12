package com.yakogdan.logisticsassistant.navigation

sealed class Screen(
    val route: String
) {

    data object Splash : Screen(ROUTE_SPLASH)
    data object Login : Screen(ROUTE_LOGIN)
    data object Tasks : Screen(ROUTE_TASKS)
    data object Schedules : Screen(ROUTE_SCHEDULES)
    data object Chat : Screen(ROUTE_CHAT)
    data object Profile : Screen(ROUTE_PROFILE)

    private companion object {
        const val ROUTE_SPLASH = "splash"
        const val ROUTE_LOGIN = "login"
        const val ROUTE_TASKS = "tasks"
        const val ROUTE_SCHEDULES = "schedules"
        const val ROUTE_CHAT = "chat"
        const val ROUTE_PROFILE = "profile"
    }
}