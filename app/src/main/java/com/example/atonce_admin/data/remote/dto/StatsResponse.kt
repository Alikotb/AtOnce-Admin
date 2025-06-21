package com.example.atonce_admin.data.remote.dto

data class StatsResponse(
    val pharmacyCount: Int,
    val revenue: Double,
    val stats: Stats
)

data class Stats(
    val Cancelled: Int,
    val Delivered: Int,
    val Ordered: Int,
    val Returned: Int
)