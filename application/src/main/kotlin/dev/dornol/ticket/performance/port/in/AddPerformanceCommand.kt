package dev.dornol.ticket.performance.port.`in`

import dev.dornol.ticket.performance.domain.PerformanceType

data class AddPerformanceCommand(
    val name: String,
    val type: PerformanceType
)
