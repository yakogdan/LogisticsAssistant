package com.yakogdan.logisticsassistant.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.yakogdan.logisticsassistant.presentation.screenstates.LoginScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

    private val _loginScreenStateFlow =
        MutableStateFlow<LoginScreenState>(LoginScreenState.Login)
    val loginScreenStateFlow = _loginScreenStateFlow.asStateFlow()

    fun changeStatePassword() {
        _loginScreenStateFlow.value = LoginScreenState.Password
    }

    fun changeStateLogin() {
        _loginScreenStateFlow.value = LoginScreenState.Login
    }
}