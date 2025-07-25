package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.port.`in`.command.MoveSeatCommand

interface MoveSeatUseCase {

    fun moveSeat(seatId: Long, command: MoveSeatCommand)

}