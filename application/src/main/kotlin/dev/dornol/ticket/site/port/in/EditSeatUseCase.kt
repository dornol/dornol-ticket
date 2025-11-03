package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.port.`in`.command.EditSeatCommand

interface EditSeatUseCase {

    fun editSeat(seatId: Long, command: EditSeatCommand)

}