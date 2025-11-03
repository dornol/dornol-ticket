package dev.dornol.ticket.performance.port.out

import dev.dornol.ticket.performance.domain.Performance

interface AddPerformancePort {

    fun add(performance: Performance)

}