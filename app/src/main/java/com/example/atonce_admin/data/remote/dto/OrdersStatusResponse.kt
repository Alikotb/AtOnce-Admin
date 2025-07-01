package com.example.atonce_admin.data.remote.dto

data class OrderStatusResponse(
    val items: List<Item>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalCount: Int,
    val totalPages: Int
)

data class Item(
    val deliveredRevenue: Double,
    val orders: List<Order>,
    val ordersCount: Int,
    val totalPrice: Double,
    val warehouseName: String
)

data class Order(
    val createdAt: String,
    val orderDetails: List<OrderDetail>,
    val orderId: Int,
    val orderState: String,
    val pharmacyName: String,
    val totalPrice: Double,
    val userName: String,
    val warehouseName: String,
    val address: String
)

data class OrderDetail(
    val medicineId: Int,
    val arabicMedicineName: String,
    val englishMedicineName: String,
    val price: Double,
    val quantity: Int
)