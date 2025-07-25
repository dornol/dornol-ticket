package dev.dornol.ticket.performance.port.out

import dev.dornol.ticket.performance.domain.Performance
import dev.dornol.ticket.performance.domain.PerformanceId

interface FindPerformancePort {

    fun findById(id: PerformanceId): Performance?

}