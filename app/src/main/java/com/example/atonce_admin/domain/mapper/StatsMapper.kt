package com.example.atonce_admin.domain.mapper

import com.example.atonce_admin.data.remote.dto.Stats
import com.example.atonce_admin.data.remote.dto.StatsResponse
import com.example.atonce_admin.domain.entity.StatsDetailsEntity
import com.example.atonce_admin.domain.entity.StatsEntity

fun StatsResponse.toEntity(): StatsEntity {
    return StatsEntity(
        pharmacyCount = pharmacyCount,
        revenue = revenue,
        stats = stats.toEntity()
    )
}

fun Stats.toEntity(): StatsDetailsEntity {
    return StatsDetailsEntity(
        cancelled = Cancelled,
        delivered = Delivered,
        ordered = Ordered,
        returned = Returned
    )
}
