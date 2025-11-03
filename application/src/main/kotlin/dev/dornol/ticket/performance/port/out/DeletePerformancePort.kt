package dev.dornol.ticket.performance.port.out

import dev.dornol.ticket.performance.domain.Performance

interface DeletePerformancePort {

    fun delete(performance: Performance, deletedBy: Long)

}