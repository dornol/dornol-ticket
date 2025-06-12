package dev.dornol.ticket.site.adapter.out.jpa.mapper

import dev.dornol.ticket.site.adapter.out.jpa.SeatOffsetEntity
import dev.dornol.ticket.site.domain.value.SeatOffset

fun SeatOffsetEntity.toDomain(): SeatOffset {
    return SeatOffset(
        x = this.x,
        y = this.y,
    )
}