package com.example.atonce_admin.domain.entity

data class UserModel(
    val address: String,
    val code: String,
    val email: String,
    val governorate: String,
    val id: Int,
    val name: String,
    val phone: String,
    val token: String?,
)