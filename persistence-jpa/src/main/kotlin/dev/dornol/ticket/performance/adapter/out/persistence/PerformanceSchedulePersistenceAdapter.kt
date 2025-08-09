package dev.dornol.ticket.performance.adapter.out.persistence

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.performance.port.`in`.dto.PerformanceScheduleListDto
import dev.dornol.ticket.performance.port.out.SearchPerformanceScheduleCriteria
import dev.dornol.ticket.performance.port.out.SearchPerformanceSchedulePort
import org.springframework.stereotype.Repository

@Repository
class PerformanceSchedulePersistenceAdapter(
    private val performanceScheduleEntityRepository: PerformanceScheduleEntityRepository
) : SearchPerformanceSchedulePort {

    override fun search(criteria: SearchPerformanceScheduleCriteria): PageResult<PerformanceScheduleListDto> {
        TODO("Not yet implemented")
    }


}