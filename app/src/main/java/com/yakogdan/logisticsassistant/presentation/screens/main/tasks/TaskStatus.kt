package com.yakogdan.logisticsassistant.presentation.screens.main.tasks

sealed class TaskStatus {
    data object New : TaskStatus()

    data object Planned : TaskStatus()

    data object InProcess : TaskStatus()

    data object Check : TaskStatus()
}