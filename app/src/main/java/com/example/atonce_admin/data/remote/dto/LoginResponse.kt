package com.example.atonce_admin.data.remote.dto


data class LoginResponse(
    val message: String?,
    val representative: Representative?,
    val success: Boolean?,
    val token: String?,
    val type: String?,
    val title: String?,
    val status:Int?,
    val errors: ResponseErrors?,
    val traceId: String?
){
    data class Representative(
        val address: String?,
        val code: String?,
        val email: String?,
        val governate: String?,
        val id: Int?,
        val name: String?,
        val phone: String?
    )
    data class ResponseErrors(
        val Password: List<String>?,
        val Email: List<String>?
    )
}

