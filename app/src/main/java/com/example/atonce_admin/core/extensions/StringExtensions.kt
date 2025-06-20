package com.example.atonce_admin.core.extensions

import java.util.Locale

fun String.convertNumbersToArabic(): String {
    return if (Locale.getDefault().language == "ar") {
        this.map { char ->
            if (char in '0'..'9') {
                ('\u0660' + (char - '0')).toChar()
            } else {
                char
            }
        }.joinToString("")
    } else {
        this
    }
}

fun String.replaceEGPWithArabicCurrency(): String {
    return this.replace("EGP", "ج.م")
}

