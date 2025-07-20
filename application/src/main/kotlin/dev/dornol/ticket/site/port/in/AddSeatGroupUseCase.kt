package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.domain.SeatGroup
import dev.dornol.ticket.site.port.`in`.command.AddSeatGroupCommand

fun interface AddSeatGroupUseCase {

    fun addSeatGroup(command: AddSeatGroupCommand): SeatGroup

}