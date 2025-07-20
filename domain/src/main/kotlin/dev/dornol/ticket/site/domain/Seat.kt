package dev.dornol.ticket.site.domain

import dev.dornol.ticket.common.domain.Domain
import dev.dornol.ticket.site.domain.value.SeatOffset

class Seat(
    override val id: SeatId,
    var name: String,
    var groupId: SeatGroupId,
    var offset: SeatOffset = SeatOffset(),
    var displayOrder: Long = 0L,
) : Domain<SeatId>(id) {

    fun copy(newSeatId: SeatId, newDisplayOrder: Long): Seat {
        return Seat(newSeatId, name, groupId, offset, newDisplayOrder)
    }

}