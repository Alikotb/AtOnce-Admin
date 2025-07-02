package com.example.atonce_admin.data.remote.dto


data class LoginResponse(
    val message: String,
    val representative: Representative,
    val success: Boolean,
    val token: String?
){
    data class Representative(
        val address: String?,
        val code: String?,
        val email: String?,
        val governate: String?,
        val id: Int?,
        val name: String?,
        val password: String?,
        val phone: String?,
    )

}


