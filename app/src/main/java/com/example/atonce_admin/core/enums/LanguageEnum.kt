package com.example.atonce_admin.core.enums

import java.util.Locale

enum class LanguageEnum(val locale: Locale, val apiCode: String, val displayNameInEnglish: String, val displayNameInArabic: String) {
    ENGLISH(Locale.ENGLISH, "en", "English", "الإنجليزية"),
    ARABIC(Locale("ar"), "ar", "Arabic", "العربية"),
    SYSTEM(Locale.getDefault(), "sys", "System language", "لغة النظام");

    fun getDisplayName(currentLanguage: LanguageEnum): String {
        return when (currentLanguage) {
            ENGLISH -> displayNameInEnglish
            ARABIC -> displayNameInArabic
            SYSTEM -> when (Locale.getDefault().language) {
                "ar" -> displayNameInArabic
                else -> displayNameInEnglish
            }
        }
    }
}