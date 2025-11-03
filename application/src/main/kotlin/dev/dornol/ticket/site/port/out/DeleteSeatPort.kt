package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.SeatId

interface DeleteSeatPort {

    fun deleteSeat(id: SeatId)

}