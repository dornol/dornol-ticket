package dev.dornol.ticket.admin.api.app.dto.seat

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.domain.entity.seat.SeatOffset

data class SeatDto(
    val id: String,
    val name: String,
    val offset: SeatOffset,
    val displayOrder: Long,
    val groupId: String
) {
    @QueryProjection
    constructor(id: Long, name: String, offset: SeatOffset, displayOrder: Long, groupId: Long) : this(id.toString(), name, offset, displayOrder, groupId.toString())
}