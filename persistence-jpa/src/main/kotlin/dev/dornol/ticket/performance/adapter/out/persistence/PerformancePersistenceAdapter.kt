package dev.dornol.ticket.performance.adapter.out.persistence

import dev.dornol.ticket.common.search.PageResult
import dev.dornol.ticket.performance.port.`in`.dto.PerformanceListDto
import dev.dornol.ticket.performance.port.out.SearchPerformancesCriteria
import dev.dornol.ticket.performance.port.out.SearchPerformancesPort
import org.springframework.stereotype.Repository

@Repository
class PerformancePersistenceAdapter(

) : SearchPerformancesPort {

    override fun searchPerformances(criteria: SearchPerformancesCriteria): PageResult<PerformanceListDto> {
        TODO("Not yet implemented")
    }

}