package dev.dornol.ticket.site.adapter.out.persistence.mapper

import dev.dornol.ticket.site.adapter.out.jpa.mapper.toDomain
import dev.dornol.ticket.site.adapter.out.persistence.SeatProjection
import dev.dornol.ticket.site.domain.SeatGroupId
import dev.dornol.ticket.site.domain.SeatId
import dev.dornol.ticket.site.port.`in`.dto.SeatDto
import dev.dornol.ticket.site.port.`in`.mapper.toDto

fun SeatProjection.toDto(): SeatDto {
    return SeatDto(
        id = SeatId(this.id),
        name = this.name,
        offset = this.offset.toDomain().toDto(),
        displayOrder = this.displayOrder,
        groupId = SeatGroupId(this.groupId)
    )
}