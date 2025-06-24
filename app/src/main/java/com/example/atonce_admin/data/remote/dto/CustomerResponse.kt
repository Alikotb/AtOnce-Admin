package com.example.atonce_admin.data.remote.dto

data class CustomerResponse(
    val pharmacies: List<Pharmacy>,
    val pharmaciesCount: Int,
    val representativeId: Int,
    val representativeName: String,
    val userName: String?
) {
    data class Pharmacy(
        val address: String,
        val areaId: Int,
        val areaName: String,
        val userName: String?,
        val governate: String,
        val id: Int,
        val name: String,
        val phoneNumber: String
    )
}