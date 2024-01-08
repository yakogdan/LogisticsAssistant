package com.yakogdan.logisticsassistant.presentation.screenstates

sealed class LoginScreenState {

    data object Login: LoginScreenState()

    data object Password: LoginScreenState()
}
