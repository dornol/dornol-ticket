package dev.dornol.ticket.reservation.domain

import dev.dornol.ticket.common.domain.Domain
import dev.dornol.ticket.common.domain.Money

class ReservationPayment(
    override val id: ReservationPaymentId,
    reservationId: ReservationId,
    price: Money,
) : Domain<ReservationPaymentId>(id)