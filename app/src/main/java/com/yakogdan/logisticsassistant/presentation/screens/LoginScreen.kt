package com.yakogdan.logisticsassistant.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yakogdan.logisticsassistant.R
import com.yakogdan.logisticsassistant.presentation.tools.mobileNumberFilter
import com.yakogdan.logisticsassistant.presentation.ui.theme.MiddleGrayBlue

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Header()
        Spacer(modifier = Modifier.height(76.dp))
        Center()
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.continue_button))
            }
        }
    }
}

@Composable
private fun Header() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo), contentDescription = stringResource(
                R.string.company_logo
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 16.sp,
            fontFamily = FontFamily(
                Font(R.font.stolzl_bold)
            ),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun Center() {
    Text(
        text = stringResource(R.string.welcome_text),
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.stolzl_regular)),
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(R.string.enter_number),
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.stolzl_book)),
        color = MaterialTheme.colorScheme.secondary
    )
    Spacer(modifier = Modifier.height(24.dp))
    val textValue = rememberSaveable {
        mutableStateOf("")
    }
    val maxChar = 10
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
        value = textValue.value,
        onValueChange = {
            if (it.length <= maxChar) textValue.value = it
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone, imeAction = ImeAction.Send
        ),
        placeholder = {
            Text(text = "999-000-00-00", color = MiddleGrayBlue)
        },
        keyboardActions = KeyboardActions(onSend = {}),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            errorBorderColor = MaterialTheme.colorScheme.error
        ),
//        supportingText = {
//            if (textValue.value.length > 4) {
//                Text(text = "Ошибка")
//            }
//        },
        visualTransformation = {
            mobileNumberFilter(it, maxChar)
        })
}

@Preview
@Composable
fun LoginScreenPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        LoginScreen()
    }
}