package dev.dornol.ticket.admin.api.app.dto.seat

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.domain.entity.seat.SeatOffsetEntity

data class SeatDto(
    val id: String,
    val name: String,
    val offset: SeatOffsetEntity,
    val displayOrder: Long,
    val groupId: String
) {
    @QueryProjection
    constructor(id: Long, name: String, offset: SeatOffsetEntity, displayOrder: Long, groupId: Long) : this(id.toString(), name, offset, displayOrder, groupId.toString())
}