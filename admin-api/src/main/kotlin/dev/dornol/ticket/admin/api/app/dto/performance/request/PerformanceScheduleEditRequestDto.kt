package dev.dornol.ticket.admin.api.app.dto.performance.request

import java.time.LocalDate
import java.time.LocalTime

data class PerformanceScheduleEditRequestDto(
    val performanceDate: LocalDate,
    val performanceTime: LocalTime
)
