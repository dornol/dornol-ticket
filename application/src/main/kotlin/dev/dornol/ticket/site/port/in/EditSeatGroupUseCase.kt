package dev.dornol.ticket.site.port.`in`

interface EditSeatGroupUseCase {

    fun editSeatGroup(id: Long, command: EditSeatGroupCommand)

}