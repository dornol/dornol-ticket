package dev.dornol.ticket.performance.port.`in`

import dev.dornol.ticket.performance.port.`in`.dto.PerformanceDetailDto

interface FindPerformanceUseCase {

    fun findById(id: Long): PerformanceDetailDto

}