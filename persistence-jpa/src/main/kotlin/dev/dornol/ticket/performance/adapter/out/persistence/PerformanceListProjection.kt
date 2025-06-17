package dev.dornol.ticket.performance.adapter.out.persistence

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.performance.domain.PerformanceType

data class PerformanceListProjection @QueryProjection constructor(
    val id: Long,
    val name: String,
    val type: PerformanceType
)
