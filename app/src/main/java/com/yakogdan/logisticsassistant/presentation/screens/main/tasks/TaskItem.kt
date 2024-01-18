package com.yakogdan.logisticsassistant.presentation.screens.main.tasks

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yakogdan.logisticsassistant.R
import java.text.DecimalFormat

@Composable
fun TaskItem(task: TaskModel, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        NumberAndCost(task)
        Spacer(modifier = Modifier.padding(4.dp))
        DateAndStatus(task)
        Spacer(modifier = Modifier.padding(8.dp))
        Route(task)
    }
}

@Composable
private fun NumberAndCost(
    task: TaskModel,
    fontSize: TextUnit = 14.sp,
    fontFamily: FontFamily = FontFamily(Font(R.font.stolzl_regular))
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Задание № ${String.format("%03d", task.taskNumber)}",
            fontSize = fontSize,
            fontFamily = fontFamily,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = task.cost.costFormat(),
            fontSize = fontSize,
            fontFamily = fontFamily,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun DateAndStatus(
    task: TaskModel,
    fontSize: TextUnit = 12.sp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = task.taskDate,
            fontSize = fontSize,
            fontFamily = FontFamily(Font(R.font.stolzl_book)),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(10.dp))
        TaskStatus(task, fontSize)
    }
}

@Composable
private fun TaskStatus(
    task: TaskModel,
    fontSize: TextUnit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(task.status.containerColor)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = task.status.name,
            fontSize = fontSize,
            fontFamily = FontFamily(Font(R.font.stolzl_regular)),
            color = task.status.textColor
        )
    }
}

@Composable
fun Route(task: TaskModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(8.dp)
    ) {
        val numberInvisibleAddresses = task.listRoutes.size - 2
        CanvasRoute(numberInvisibleAddresses)
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            val firstAddress = task.listRoutes[0]
            AddressAndDate(address = firstAddress.address, date = firstAddress.date)
            Spacer(modifier = Modifier.height(8.dp))
            val lastAddress = task.listRoutes[task.listRoutes.size-1]
            AddressAndDate(address = lastAddress.address, date = lastAddress.date)
        }
    }
}

@Composable
fun AddressAndDate(
    address: String,
    date: String,
    fontFamily: FontFamily = FontFamily(Font(R.font.stolzl_book))
) {
    Column {
        Text(
            text = address,
            fontSize = 14.sp,
            fontFamily = fontFamily,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = date,
            fontSize = 12.sp,
            fontFamily = fontFamily,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}

@Composable
fun CanvasRoute(numberInvisibleAddresses: Int) {
    val color = MaterialTheme.colorScheme.outline
    val strokeWidthDp = 1.3.dp
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = textMeasurer
        .measure(
            text = numberInvisibleAddresses.toString(),
            style = TextStyle(
                color = color,
                fontSize = 8.sp,
                fontFamily = FontFamily(Font(R.font.stolzl_regular))
            )
        )
    Canvas(modifier = Modifier.padding(top = 10.dp, start = 8.dp, end = 3.dp)) {
        drawCircle(
            color = color,
            radius = 3.dp.toPx(),
            style = Stroke(width = strokeWidthDp.toPx())
        )
        if (numberInvisibleAddresses > 0) {
            drawLine(
                color = color,
                start = Offset(0f, 3.dp.toPx()),
                end = Offset(0f, 16.dp.toPx()),
                strokeWidth = strokeWidthDp.toPx()
            )
            drawCircle(
                color = color,
                radius = 6.dp.toPx(),
                style = Stroke(width = strokeWidthDp.toPx()),
                center = Offset(0f, 22.dp.toPx())
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(-2.3.dp.toPx(), 16.dp.toPx())
            )
            drawLine(
                color = color,
                start = Offset(0f, 28.dp.toPx()),
                end = Offset(0f, 40.dp.toPx()),
                strokeWidth = strokeWidthDp.toPx()
            )
        } else {
            drawLine(
                color = color,
                start = Offset(0f, 3.dp.toPx()),
                end = Offset(0f, 40.dp.toPx()),
                strokeWidth = strokeWidthDp.toPx()
            )
        }
        drawCircle(
            color = color,
            radius = 3.dp.toPx(),
            style = Fill,
            center = Offset(0f, 43.dp.toPx())
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