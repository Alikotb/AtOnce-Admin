package com.example.atonce_admin.data.remote.dto

data class PharmacyOrdersResponse(
    val message: String,
    val result: PharmacyOrdersItem
)


data class PharmacyOrdersItem(
    val items: List<PharmacyOrderDto?>,
)

data class PharmacyOrderDto(
    val createdAt: String,
    val orderId: Int,
    val quantity: Int,
    val status: String,
    val totalPrice: Double,
    val wareHouseImage: String,
    val wareHouseName: String
)