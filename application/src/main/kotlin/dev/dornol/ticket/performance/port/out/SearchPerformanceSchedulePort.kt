package dev.dornol.ticket.performance.port.out

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.performance.port.`in`.dto.PerformanceScheduleListDto

interface SearchPerformanceSchedulePort {

    fun search(criteria: SearchPerformanceScheduleCriteria): PageResult<PerformanceScheduleListDto>

}