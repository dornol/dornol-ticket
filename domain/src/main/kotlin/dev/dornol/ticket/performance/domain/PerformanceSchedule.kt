package dev.dornol.ticket.performance.domain

import dev.dornol.ticket.common.domain.Domain
import dev.dornol.ticket.site.domain.SiteId
import java.time.LocalDate
import java.time.LocalTime

class PerformanceSchedule(
    override val id: PerformanceScheduleId,
    val performanceId: PerformanceId,
    val siteId: SiteId,
    var date: LocalDate,
    var time: LocalTime
) : Domain<PerformanceScheduleId>(id)