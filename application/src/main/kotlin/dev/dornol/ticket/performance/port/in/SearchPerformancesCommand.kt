package dev.dornol.ticket.performance.port.`in`

import dev.dornol.ticket.common.search.PageQuery
import dev.dornol.ticket.common.search.SearchBaseCommand

class SearchPerformancesCommand(
    override val searchKeys: Set<PerformanceSearchField>,
    override val searchText: String,
    override val pageQuery: PageQuery
) : SearchBaseCommand<PerformanceSearchField>