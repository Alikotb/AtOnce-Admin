package com.example.atonce_admin.domain.entity

data class OrderStateEntity(
    val items: List<WarehouseEntity>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int,
    val totalPages: Int
)

data class WarehouseEntity(
    val deliveredRevenue: Double,
    val orders: List<OrderEntity>,
    val ordersCount: Int,
    val totalPrice: Double,
    val warehouseName: String
)

data class OrderEntity(
    val createdAt: String,
    val orderDetails: List<OrderDetailEntity>,
    val orderId: Int,
    val orderState: String,
    var pharmacyName: String,
    val totalPrice: Double,
    var userName: String,
    val warehouseName: String,
    var address: String
)

data class OrderDetailEntity(
    val medicineId: Int,
    val medicineName: String,
    val price: Double,
    val quantity: Int
)

data class PharmacyOrderResponseEntity(
    val message: String,
    val result: List<OrderEntity>
)

