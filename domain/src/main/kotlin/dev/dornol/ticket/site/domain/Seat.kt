package dev.dornol.ticket.site.domain

import dev.dornol.ticket.common.domain.Domain
import dev.dornol.ticket.site.domain.value.SeatOffset
import kotlin.math.min

private const val IMAGE_AREA_SIZE = 600.0
private const val BOX_SIZE = 40.0

class Seat(
    override val id: SeatId,
    var name: String,
    var groupId: SeatGroupId,
    var offset: SeatOffset = SeatOffset(),
    var displayOrder: Long = 0L,
) : Domain<SeatId>(id) {

    fun copy(newSeatId: SeatId, newDisplayOrder: Long): Seat {
        return Seat(
            newSeatId,
            name,
            groupId,
            offset = SeatOffset(
                x = min(IMAGE_AREA_SIZE - BOX_SIZE, this.offset.x + 10L),
                y = min(IMAGE_AREA_SIZE - BOX_SIZE, this.offset.y + 10)
            ),
            newDisplayOrder
        )
    }

    fun moveTo(x: Double, y: Double) {
        offset = SeatOffset(x, y)
    }

    fun edit(name: String, groupId: SeatGroupId) {
        this.name = name
        this.groupId = groupId
    }

}