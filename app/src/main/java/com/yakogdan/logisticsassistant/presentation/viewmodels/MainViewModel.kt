package com.yakogdan.logisticsassistant.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.yakogdan.logisticsassistant.presentation.screenstates.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _authStateFlow =
        MutableStateFlow<AuthState>(AuthState.Initial)
    val authStateFlow = _authStateFlow.asStateFlow()
}