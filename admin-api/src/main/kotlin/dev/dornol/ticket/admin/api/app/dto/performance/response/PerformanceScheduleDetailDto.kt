package dev.dornol.ticket.admin.api.app.dto.performance.response

import dev.dornol.ticket.admin.api.app.dto.site.response.AddressDto
import dev.dornol.ticket.admin.api.app.dto.site.response.SiteDto
import dev.dornol.ticket.domain.entity.performance.PerformanceSchedule
import java.time.LocalDate
import java.time.LocalTime

class PerformanceScheduleDetailDto(
    val performance: PerformanceDetailDto,
    val site: SiteDto,
    val performanceDate: LocalDate,
    val performanceTime: LocalTime,
) {

    constructor(schedule: PerformanceSchedule) : this(
        performance = PerformanceDetailDto(schedule.performance),
        site = SiteDto(
            schedule.site.id!!,
            schedule.site.name,
            AddressDto(
                schedule.site.address.zipCode,
                schedule.site.address.mainAddress,
                schedule.site.address.detailAddress
            ),
            schedule.site.seatingMapFile.uuid.toString()
        ),
        performanceDate = schedule.performanceDate,
        performanceTime = schedule.performanceTime,
    )


}