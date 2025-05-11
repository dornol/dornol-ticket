package dev.dornol.ticket.admin.api.app.dto.performance.response

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.admin.api.app.dto.site.response.SiteListDto
import java.time.LocalDate
import java.time.LocalTime

data class PerformanceScheduleListDto @QueryProjection constructor(
    val id: Long,
    val performance: PerformanceListDto,
    val site: SiteListDto,
    val date: LocalDate,
    val time: LocalTime
)
