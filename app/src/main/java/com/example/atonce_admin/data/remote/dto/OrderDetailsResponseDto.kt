package com.example.atonce_admin.data.remote.dto

data class OrderDetailsResponseDto(
    val message: String,
    val result: List<OrderItemDto>
)

data class OrderItemDto(
    val arabicMedicineName: String,
    val medicineName: String,
    val quantity: Int,
    val totalPriceAfterDisccount: Double,
    val totalPriceBeforeDisccount: Double,
    val medicinePrice: Double,
    val medicineImage: String,
    val discountAmount: Double
)