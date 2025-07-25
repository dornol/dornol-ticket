package dev.dornol.ticket.performance.adapter.out.persistence

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.performance.domain.PerformanceId
import dev.dornol.ticket.performance.domain.PerformanceType
import dev.dornol.ticket.performance.port.`in`.dto.PerformanceListDto

data class PerformanceListProjection @QueryProjection constructor(
    val id: Long,
    val name: String,
    val type: PerformanceType
) {
    fun toPerformanceListDto() = PerformanceListDto(PerformanceId(id), name, type)
}
