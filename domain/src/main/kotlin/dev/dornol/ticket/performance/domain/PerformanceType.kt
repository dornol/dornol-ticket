package dev.dornol.ticket.performance.domain

import dev.dornol.ticket.common.domain.BaseEnum

enum class PerformanceType(override val code: Int) : BaseEnum<Int> {
    MOVIE(100),
    PERFORMANCE(200),
    CONCERT(300)
}