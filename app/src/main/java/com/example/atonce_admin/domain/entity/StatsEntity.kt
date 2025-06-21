package com.example.atonce_admin.domain.entity

data class StatsEntity(
    val pharmacyCount: Int,
    val revenue: Double,
    val stats: StatsDetailsEntity
)

data class StatsDetailsEntity(
    val cancelled: Int,
    val delivered: Int,
    val ordered: Int,
    val returned: Int
)
