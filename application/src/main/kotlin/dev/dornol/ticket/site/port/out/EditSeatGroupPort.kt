package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.SeatGroup

interface EditSeatGroupPort {

    fun editSeatGroup(seatGroup: SeatGroup)

}