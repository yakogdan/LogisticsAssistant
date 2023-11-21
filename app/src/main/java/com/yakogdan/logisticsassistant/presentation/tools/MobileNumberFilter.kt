package com.yakogdan.logisticsassistant.presentation.tools

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import com.yakogdan.logisticsassistant.presentation.ui.theme.MiddleGrayBlue

fun mobileNumberFilter(text: AnnotatedString, maxChar: Int): TransformedText {
    val dash = "-"
    val mask = "999${dash}000${dash}00${dash}00" // 999 - 000 - 00 - 00
    val trimmed =
        if (text.text.length >= maxChar) text.text.substring(0 until maxChar) else text.text
    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i == 2 || i == 5 || i == 7) {
                append(dash)
            }
        }
        pushStyle(SpanStyle(color = MiddleGrayBlue))
        if (length > 0) {
            append(mask.takeLast(mask.length - length))
        }
        toAnnotatedString()
    }
    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 5) return offset + dash.length
            if (offset <= 7) return offset + (2 * dash.length)
            if (offset <= maxChar) return offset + (3 * dash.length)
            return mask.length
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 5) return offset - dash.length
            if (offset <= 7) return offset - (2 * dash.length)
            if (offset <= maxChar) return offset - (3 * dash.length)
            return maxChar
        }
    }
    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}

//fun mobileNumberFilter(text: AnnotatedString): TransformedText {
//    val mask = "+7 (999) 000 - 00 - 00"
//    val trimmed = if (text.text.length >= 10) text.text.substring(0..9) else text.text
//    val out = AnnotatedString.Builder().run {
//        for (i in trimmed.indices) {
//            if (i == 0) {
//                append("+7 (")
//            }
//            append(trimmed[i])
//            when (i) {
//                2 -> {
//                    append(") ")
//                }
//
//                5, 7 -> {
//                    append(" - ")
//                }
//
//                else -> {}
//            }
//        }
//        pushStyle(SpanStyle(color = MiddleGrayBlue))
//        if (length > 0) {
//            append(mask.takeLast(mask.length - length))
//        }
//        toAnnotatedString()
//    }
//    val phoneNumberOffsetTranslator = object : OffsetMapping {
//        override fun originalToTransformed(offset: Int): Int {
//            if (offset == 0) return offset
//            if (offset <= 2) return offset + 4
//            if (offset <= 5) return offset + 6
//            if (offset <= 7) return offset + 9
//            if (offset <= 10) return offset + 12
//            return 22
//        }
//
//        override fun transformedToOriginal(offset: Int): Int {
//            if (offset == 0) return offset
//            if (offset <= 2) return offset - 4
//            if (offset <= 5) return offset - 6
//            if (offset <= 7) return offset - 9
//            if (offset <= 10) return offset - 12
//            return 10
//        }
//    }
//    return TransformedText(out, phoneNumberOffsetTranslator)
//}