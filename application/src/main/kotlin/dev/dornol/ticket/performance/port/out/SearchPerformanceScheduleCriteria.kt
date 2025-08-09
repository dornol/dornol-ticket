package dev.dornol.ticket.performance.port.out

import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SearchBaseCriteria
import dev.dornol.ticket.performance.domain.PerformanceType
import dev.dornol.ticket.performance.port.`in`.PerformanceScheduleSearchField
import java.time.LocalDate

data class SearchPerformanceScheduleCriteria(
    override val searchKeys: Set<PerformanceScheduleSearchField>,
    override val searchText: String,
    override val pageQuery: PageQuery,
    val performanceDateStart: LocalDate?,
    val performanceDateEnd: LocalDate?,
    val performanceType: PerformanceType?
) : SearchBaseCriteria<PerformanceScheduleSearchField>
