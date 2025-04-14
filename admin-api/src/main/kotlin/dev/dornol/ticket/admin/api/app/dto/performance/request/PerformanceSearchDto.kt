package dev.dornol.ticket.admin.api.app.dto.performance.request

import dev.dornol.ticket.admin.api.app.dto.common.request.DefaultSearchDto

class PerformanceSearchDto(
    searchFields: Set<PerformanceSearchField> = hashSetOf(),
    searchText: String = "",
    val siteId: Long?,
) : DefaultSearchDto<PerformanceSearchField>(searchFields, searchText)
