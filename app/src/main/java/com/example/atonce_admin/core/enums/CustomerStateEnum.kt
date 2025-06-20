package com.example.atonce_admin.core.enums

import androidx.compose.ui.graphics.Color
import java.util.Locale

enum class CustomerStateEnum(val value: String , val arabicValue : String , val color : Color) {
    ACTIVE("Active", "نشط" , Color(0xFF00B259)),
    Suspended("Suspended", "معلق" , Color(0xFFFE7E0F));

    fun getLocalizedValue(): String {
        return if (Locale.getDefault().language == "ar") arabicValue else value
    }
}