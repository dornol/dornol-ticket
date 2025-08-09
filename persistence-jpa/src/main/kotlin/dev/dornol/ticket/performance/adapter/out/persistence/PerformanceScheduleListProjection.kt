package dev.dornol.ticket.performance.adapter.out.persistence

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.site.adapter.out.persistence.SiteListProjection
import java.time.LocalDate
import java.time.LocalTime

@QueryProjection
data class PerformanceScheduleListProjection(
    val id: Long,
    val performance: PerformanceListProjection,
    val site: SiteListProjection,
    val date: LocalDate,
    val time: LocalTime
)
