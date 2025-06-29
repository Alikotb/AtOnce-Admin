package com.example.atonce_admin.domain.entity

data class OrderDetails(
    val message: String,
    val result: List<OrderItem>
)

data class OrderItem(
    val arabicMedicineName: String,
    val medicineName: String,
    val quantity: Int,
    val totalPriceAfterDisccount: Double,
    val totalPriceBeforeDisccount: Double,
    val medicinePrice: Double,
    val medicineImage: String,
    val discountAmount: Double
)


