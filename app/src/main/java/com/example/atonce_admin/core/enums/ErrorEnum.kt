package com.example.atonce_admin.core.enums

import java.util.Locale

enum class ErrorEnum(val enErrorMessage: String , val arErrorMessage: String) {

    NETWORK_ERROR( "Please Check your Internet Connection" , "يرجى التحقق من اتصالك بالإنترنت");

    fun getLocalizedMessage(): String {
        return if (Locale.getDefault().language == "ar") {
            arErrorMessage
        } else {
            enErrorMessage
        }
    }
}