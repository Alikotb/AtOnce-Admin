package com.example.atonce_admin.domain.mapper

import com.example.atonce_admin.data.remote.dto.*
import com.example.atonce_admin.domain.entity.*

fun OrderStatusResponse.toEntity(): OrderStateEntity {
    return OrderStateEntity(
        items = items.map { it.toEntity() },
        pageNumber = pageNumber,
        pageSize = pageSize,
        totalCount = totalCount,
        totalPages = totalPages
    )
}

fun Item.toEntity(): WarehouseEntity {
    return WarehouseEntity(
        deliveredRevenue = deliveredRevenue,
        orders = orders.map { it.toEntity() },
        ordersCount = ordersCount,
        totalPrice = totalPrice,
        warehouseName = warehouseName
    )
}

fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        createdAt = createdAt,
        orderDetails = orderDetails.map { it.toEntity() },
        orderId = orderId,
        orderState = orderState,
        pharmacyName = pharmacyName,
        totalPrice = totalPrice,
        userName = userName,
        warehouseName = warehouseName
    )
}

fun OrderDetail.toEntity(): OrderDetailEntity {
    return OrderDetailEntity(
        medicineId = medicineId,
        medicineName = medicineName,
        price = price,
        quantity = quantity
    )
}
