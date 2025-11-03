package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.SeatGroup
import dev.dornol.ticket.site.domain.SeatGroupId

interface DeleteSeatGroupPort {

    fun deleteSeatGroup(searGroup: SeatGroup, deletedBy: Long)

}