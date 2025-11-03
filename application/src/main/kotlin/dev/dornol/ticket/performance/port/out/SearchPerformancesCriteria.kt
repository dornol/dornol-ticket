package dev.dornol.ticket.performance.port.out

import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SearchBaseCriteria
import dev.dornol.ticket.performance.port.`in`.PerformanceSearchField

data class SearchPerformancesCriteria(
    override val searchKeys: Set<PerformanceSearchField>,
    override val searchText: String,
    override val pageQuery: PageQuery,
    val companyId: Long
) : SearchBaseCriteria<PerformanceSearchField>