package dev.dornol.ticket.performance.port.`in`.dto

import dev.dornol.ticket.performance.domain.PerformanceScheduleId
import dev.dornol.ticket.site.port.`in`.dto.SiteListDto
import java.time.LocalDate
import java.time.LocalTime

data class PerformanceScheduleListDto(
    val id: PerformanceScheduleId,
    val performance: PerformanceListDto,
    val site: SiteListDto,
    val date: LocalDate,
    val time: LocalTime
)
