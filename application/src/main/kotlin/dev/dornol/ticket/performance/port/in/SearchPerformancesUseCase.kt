package dev.dornol.ticket.performance.port.`in`

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.performance.port.`in`.dto.PerformanceListDto

fun interface SearchPerformancesUseCase {

    fun searchPerformances(command: SearchPerformancesCommand): PageResult<PerformanceListDto>

}