package dev.dornol.ticket.performance.domain

import dev.dornol.ticket.common.domain.Domain
import dev.dornol.ticket.common.domain.Money
import dev.dornol.ticket.site.domain.SeatGroupId

class PerformanceScheduleSeatGroup(
    override val id: PerformanceScheduleSeatGroupId,
    val performanceScheduleId: PerformanceScheduleId,
    val seatGroupId: SeatGroupId,
    var price: Money
) : Domain<PerformanceScheduleSeatGroupId>(id) {
}