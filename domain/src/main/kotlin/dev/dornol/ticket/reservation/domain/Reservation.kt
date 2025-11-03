package dev.dornol.ticket.reservation.domain

import dev.dornol.ticket.common.domain.Domain
import dev.dornol.ticket.common.domain.Money
import dev.dornol.ticket.performance.domain.PerformanceScheduleSeatGroupId
import dev.dornol.ticket.site.domain.SeatId

class Reservation(
    override val id: ReservationId,
    val performanceScheduleSeatGroupId: PerformanceScheduleSeatGroupId,
    val seatId: SeatId,
    val price: Money,
    var state: ReservationStatus = ReservationStatus.PENDING_PAYMENT,
) : Domain<ReservationId>(id)