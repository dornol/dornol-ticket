package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.domain.Seat

interface DuplicateSeatUseCase {

    fun duplicateSeat(seatId: Long): Seat

}