package com.yakogdan.logisticsassistant.presentation.screens.main.tasks

import androidx.compose.ui.graphics.Color
import com.yakogdan.logisticsassistant.presentation.ui.theme.BlueItem
import com.yakogdan.logisticsassistant.presentation.ui.theme.GreenItem
import com.yakogdan.logisticsassistant.presentation.ui.theme.OnBlueItem
import com.yakogdan.logisticsassistant.presentation.ui.theme.OnGreenItem
import com.yakogdan.logisticsassistant.presentation.ui.theme.OnPinkItem
import com.yakogdan.logisticsassistant.presentation.ui.theme.OnYellowItem
import com.yakogdan.logisticsassistant.presentation.ui.theme.PinkItem
import com.yakogdan.logisticsassistant.presentation.ui.theme.YellowItem

sealed class TaskStatus(
    val name: String,
    val containerColor: Color,
    val textColor: Color
) {
    data object New : TaskStatus(
        name = "Новое",
        containerColor = GreenItem,
        textColor = OnGreenItem
    )

    data object Planned : TaskStatus(
        name = "Запланированные",
        containerColor = BlueItem,
        textColor = OnBlueItem
    )

    data object InProcess : TaskStatus(
        name = "В процессе",
        containerColor = PinkItem,
        textColor = OnPinkItem
    )

    data object Check : TaskStatus(
        name = "Проверка",
        containerColor = YellowItem,
        textColor = OnYellowItem
    )
}