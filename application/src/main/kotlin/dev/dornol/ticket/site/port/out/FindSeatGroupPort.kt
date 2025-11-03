package dev.dornol.ticket.site.port.out

import dev.dornol.ticket.site.domain.SeatGroup
import dev.dornol.ticket.site.domain.SeatGroupId

interface FindSeatGroupPort {

    fun findSeatGroup(id: SeatGroupId): SeatGroup?

}