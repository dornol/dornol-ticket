package dev.dornol.ticket.admin.api.app.dto.performance.request

import dev.dornol.ticket.admin.api.app.dto.common.request.DefaultSearchDto

class PerformanceSearchDto(
    searchFields: Set<PerformanceSearchField> = hashSetOf(),
    searchText: String = ""
) : DefaultSearchDto<PerformanceSearchField>(searchFields, searchText)
