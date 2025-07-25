package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.domain.Seat
import dev.dornol.ticket.site.port.`in`.command.AddSeatCommand

interface AddSeatUseCase {

    fun addSeat(command: AddSeatCommand): Seat

}