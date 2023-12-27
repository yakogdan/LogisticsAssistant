package com.yakogdan.logisticsassistant.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.yakogdan.logisticsassistant.R
import com.yakogdan.logisticsassistant.presentation.tools.mobileNumberFilter
import com.yakogdan.logisticsassistant.presentation.tools.passwordFilter

@Composable
fun LoginScreen() {
    val isActive = rememberSaveable {
        mutableStateOf(false)
    }
    val textValueLogin = rememberSaveable {
        mutableStateOf("")
    }
    val textValuePassword = rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(40.dp))
            Header()

            CenterPassword(isActive, textValuePassword)
            Spacer(modifier = Modifier.height(20.dp))
        }
         ContinueButton(isActive.value, textValuePassword.value)
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
private fun CenterLogin(isActive: MutableState<Boolean>) {
    Spacer(modifier = Modifier.height(76.dp))
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
            if (it.length <= maxChar && it.isDigitsOnly()) textValue.value = it
            isActive.value = it.length == maxChar
        },
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.stolzl_regular)),
            color = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(26.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone, imeAction = ImeAction.Send
        ),
        placeholder = {
            Text(
                text = "999-000-00-00", style = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.stolzl_regular)),
                    color = MaterialTheme.colorScheme.onTertiary
                )
            )
        },
        keyboardActions = KeyboardActions(onSend = {}),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            errorBorderColor = MaterialTheme.colorScheme.error
        ),
        visualTransformation = {
            mobileNumberFilter(it, maxChar)
        })
}

@Composable
private fun CenterPassword(isActive: MutableState<Boolean>, textValue: MutableState<String>) {
    Spacer(modifier = Modifier.height(88.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }
        Text(
            text = stringResource(R.string.enter_password),
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.stolzl_regular)),
            color = MaterialTheme.colorScheme.onBackground
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = stringResource(R.string.get_password),
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.stolzl_book)),
        color = MaterialTheme.colorScheme.secondary
    )

    Spacer(modifier = Modifier.height(24.dp))

    val letterSpacing = TextUnit(9.0f, TextUnitType.Sp)
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue.value,
        onValueChange = {
            if (it.length <= 6 && it.isDigitsOnly()) textValue.value = it
            isActive.value = it.trim().length >= 6
        },
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.stolzl_regular)),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            letterSpacing = letterSpacing
        ),
        shape = RoundedCornerShape(26.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone, imeAction = ImeAction.Send
        ),
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "______",
                style = TextStyle.Default.copy(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.stolzl_regular)),
                    color = MaterialTheme.colorScheme.onBackground
                ),
                letterSpacing = letterSpacing
            )
        },
        keyboardActions = KeyboardActions(onSend = { sendData(textValue.value) }),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
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
            passwordFilter(it)
        }
    )
}

@Composable
private fun ContinueButton(isActive: Boolean, textValue: String) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        Button(
            modifier = Modifier.height(54.dp),
            enabled = isActive,
            onClick = { sendData(textValue) },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.continue_button), fontSize = 16.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

fun sendData(textValue: String) {
    if (textValue.length == 6) {
        Log.d("myTAG", "ContinueButton: $textValue")
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        LoginScreen()
    }
}