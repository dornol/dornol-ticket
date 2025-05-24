package dev.dornol.ticket.admin.api.app.dto.performance.request

import java.time.LocalDate
import java.time.LocalTime

data class PerformanceScheduleAddRequestDto(
    val performanceId: Long,
    val siteId: Long,
    val performanceDate: LocalDate,
    val performanceTime: LocalTime
)
