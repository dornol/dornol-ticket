package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.SeatGroup

interface AddSeatGroupPort {

    fun addSeatGroup(seatGroup: SeatGroup)

}