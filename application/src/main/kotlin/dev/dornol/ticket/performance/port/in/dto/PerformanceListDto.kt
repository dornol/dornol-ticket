package dev.dornol.ticket.performance.port.`in`.dto

import dev.dornol.ticket.performance.domain.PerformanceId
import dev.dornol.ticket.performance.domain.PerformanceType

data class PerformanceListDto(
    val id: PerformanceId,
    val name: String,
    val type: PerformanceType
)
