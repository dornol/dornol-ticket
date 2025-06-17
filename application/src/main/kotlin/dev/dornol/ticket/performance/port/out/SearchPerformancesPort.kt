package dev.dornol.ticket.performance.port.out

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.performance.port.`in`.dto.PerformanceListDto

interface SearchPerformancesPort {

    fun searchPerformances(criteria: SearchPerformancesCriteria): PageResult<PerformanceListDto>

}