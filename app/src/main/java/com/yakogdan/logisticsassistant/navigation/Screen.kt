package com.yakogdan.logisticsassistant.navigation

sealed class Screen(
    val route: String
) {

    data object Splash : Screen(ROUTE_SPLASH)
    data object Login : Screen(ROUTE_LOGIN)

    private companion object {
        const val ROUTE_SPLASH = "splash"
        const val ROUTE_LOGIN = "login"
    }
}