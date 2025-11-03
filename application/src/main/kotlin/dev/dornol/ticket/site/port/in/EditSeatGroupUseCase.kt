package dev.dornol.ticket.site.port.`in`

import dev.dornol.ticket.site.port.`in`.command.EditSeatGroupCommand

interface EditSeatGroupUseCase {

    fun editSeatGroup(id: Long, command: EditSeatGroupCommand)

}