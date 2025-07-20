package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.Seat

interface FindSeatPort {

    fun findSeat(seatId: Long): Seat?

}