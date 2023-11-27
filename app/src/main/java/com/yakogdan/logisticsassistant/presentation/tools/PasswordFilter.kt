package com.yakogdan.logisticsassistant.presentation.tools

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import com.yakogdan.logisticsassistant.presentation.ui.theme.Dark

fun passwordFilter(text: AnnotatedString): TransformedText {
    val mask = "______"
    val trimmed =
        if (text.text.length >= 6) text.text.substring(0 until 6) else text.text
    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
        }
        pushStyle(SpanStyle(color = Dark))
        if (length > 0) {
            append(mask.takeLast(mask.length - length))
        }
        toAnnotatedString()
    }

    val passwordOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset
        }
        override fun transformedToOriginal(offset: Int): Int {
            return offset
        }
    }
    return TransformedText(annotatedString, passwordOffsetTranslator)
}