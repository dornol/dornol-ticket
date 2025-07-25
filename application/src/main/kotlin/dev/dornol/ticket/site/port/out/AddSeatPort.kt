package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.Seat

interface AddSeatPort {

    fun addSeat(seat: Seat)

}