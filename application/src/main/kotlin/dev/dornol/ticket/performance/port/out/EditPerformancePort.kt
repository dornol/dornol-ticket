package dev.dornol.ticket.performance.port.out

import dev.dornol.ticket.performance.domain.Performance

interface EditPerformancePort {

    fun edit(performance: Performance)

}