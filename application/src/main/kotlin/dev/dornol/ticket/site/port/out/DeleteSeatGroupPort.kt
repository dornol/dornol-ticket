package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.SeatGroupId

interface DeleteSeatGroupPort {

    fun deleteSeatGroup(id: SeatGroupId)

}