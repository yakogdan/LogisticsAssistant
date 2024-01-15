package com.yakogdan.logisticsassistant.presentation.screens.main.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yakogdan.logisticsassistant.R
import java.text.DecimalFormat

@Composable
fun TaskItem(task: TaskModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Задание № ${String.format("%03d", task.taskNumber)}",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.stolzl_medium)),
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = task.cost.costFormat(),
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.stolzl_medium)),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.taskDate,
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.stolzl_regular)),
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.padding(10.dp))
            TaskStatus(task)
        }
    }
    Spacer(modifier = Modifier.padding(top = 8.dp))
}

@Composable
private fun TaskStatus(task: TaskModel) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(task.status.containerColor)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = task.status.name,
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.stolzl_regular)),
            color = task.status.textColor
        )
    }
}

fun Int.costFormat(): String {
    return if (this > 999) {
        val dec = DecimalFormat("#,###.00 ₽")
        dec.format(this)
    } else {
        val dec = DecimalFormat("#.00 ₽")
        dec.format(this)
    }
}