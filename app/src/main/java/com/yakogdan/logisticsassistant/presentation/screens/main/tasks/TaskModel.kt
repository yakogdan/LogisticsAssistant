package com.yakogdan.logisticsassistant.presentation.screens.main.tasks

data class TaskModel(
    val taskNumber: Int,
    val cost: Int,
    val taskDate: String,
    val status: TaskStatus,
    val listRoutes: List<RouteModel>
)
