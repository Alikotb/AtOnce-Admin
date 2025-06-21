package com.example.atonce_admin.core.extensions

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

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
    return if (Locale.getDefault().language == "ar"){
        this.replace("EGP", "ج.م")
    }
    else{
         this
    }

}


fun String.toLocalizedDateTime(): String {
    return try {
        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSX", Locale.US)
        isoFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = isoFormat.parse(this)

        val currentLocale = Locale.getDefault().language

        val pattern = if (currentLocale == "ar") {
            "dd MMM yyyy - hh:mm a"
        } else {
            "MMM dd, yyyy - hh:mm a"
        }

        val formatter = SimpleDateFormat(pattern, Locale(currentLocale))
        formatter.format(date!!)
    } catch (e: Exception) {
        this
    }
}


