package com.example.atonce_admin.data

import com.example.atonce_admin.data.Response

sealed class Response {
    data class Success<T>(val data: T) : Response()
    data class Error(val message: String) : Response()
    object Loading : Response()
}