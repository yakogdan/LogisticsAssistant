package com.yakogdan.logisticsassistant.presentation.screens.main.tasks

sealed class TaskStatus(val name: String) {
    data object New : TaskStatus("Новое")

    data object Planned : TaskStatus("Запланированные")

    data object InProcess : TaskStatus("В процессе")

    data object Check : TaskStatus("Проверка")
}