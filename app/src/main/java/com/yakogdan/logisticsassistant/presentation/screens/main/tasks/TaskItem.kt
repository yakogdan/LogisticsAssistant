package com.yakogdan.logisticsassistant.presentation.screens.main.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TaskItem(task: TaskModel) {
    Column {
        Text(text = "Задание № ${String.format("%03d", task.taskNumber)}")
    }
}