package com.example.atonce_admin.core.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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


@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalizedDateTime(): String {
    return try {
        val isoString = if (this.endsWith("Z").not()) "${this}Z" else this

        val instant = Instant.parse(isoString)
        val dateTime = instant.atZone(ZoneId.systemDefault())

        val currentLocale = Locale.getDefault().language
        val pattern = if (currentLocale == "ar") {
            "dd MMM yyyy - hh:mm a"
        } else {
            "MMM dd, yyyy - hh:mm a"
        }

        val formatter = DateTimeFormatter.ofPattern(pattern, Locale(currentLocale))
        dateTime.format(formatter)
    } catch (e: Exception) {
        this // fallback to original string
    }
}



