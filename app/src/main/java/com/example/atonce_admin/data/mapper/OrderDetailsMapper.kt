package com.example.atonce_admin.data.mapper

import com.example.atonce_admin.data.remote.dto.OrderDetail
import com.example.atonce_admin.data.remote.dto.OrderDetailsResponseDto
import com.example.atonce_admin.data.remote.dto.OrderItemDto
import com.example.atonce_admin.domain.entity.OrderDetailEntity
import com.example.atonce_admin.domain.entity.OrderDetails
import com.example.atonce_admin.domain.entity.OrderItem

fun OrderDetailsResponseDto.toEntity(): OrderDetails {
    return OrderDetails(
        message = message,
        result = result.map { it.toEntity() }
    )
}

fun OrderItemDto.toEntity(): OrderItem {
    return OrderItem(
        arabicMedicineName = arabicMedicineName,
        medicineName = medicineName,
        quantity = quantity,
        totalPriceAfterDisccount = totalPriceAfterDisccount * quantity,
        totalPriceBeforeDisccount = totalPriceBeforeDisccount,
        medicinePrice = medicinePrice,
        medicineImage = medicineImage,
        discountAmount = discountAmount
    )
}

fun OrderDetailEntity.toEntity(): OrderItem {
    return OrderItem(
        arabicMedicineName = medicineName,
        medicineName = medicineName,
        quantity = quantity,
        totalPriceAfterDisccount = price * quantity,
        totalPriceBeforeDisccount = 0.0,
        medicinePrice = 0.0,
        medicineImage = "",
        discountAmount = 0.0
    )
}
