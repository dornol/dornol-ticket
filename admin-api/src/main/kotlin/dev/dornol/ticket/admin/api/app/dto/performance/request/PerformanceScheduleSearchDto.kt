package dev.dornol.ticket.admin.api.app.dto.performance.request

import dev.dornol.ticket.admin.api.app.dto.common.request.DefaultSearchDto
import dev.dornol.ticket.performance.domain.PerformanceType
import dev.dornol.ticket.performance.port.`in`.PerformanceScheduleSearchField
import java.time.LocalDate

class PerformanceScheduleSearchDto(
    searchFields: Set<PerformanceScheduleSearchField> = hashSetOf(),
    searchText: String = "",
    val performanceDateStart: LocalDate? = null,
    val performanceDateEnd: LocalDate? = null,
    val performanceType: PerformanceType? = null
) : DefaultSearchDto<PerformanceScheduleSearchField>(searchFields, searchText)