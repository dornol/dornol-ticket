package dev.dornol.ticket.site.adapter.out.persistence.mapper

import dev.dornol.ticket.site.adapter.out.persistence.SeatGroupProjection
import dev.dornol.ticket.site.domain.SeatGroupId
import dev.dornol.ticket.site.port.`in`.dto.SeatGroupDto

fun SeatGroupProjection.toDto(): SeatGroupDto {
    return SeatGroupDto(
        id = SeatGroupId(this.id),
        name = this.name,
        color = this.color,
        displayOrder = this.displayOrder,
    )
}