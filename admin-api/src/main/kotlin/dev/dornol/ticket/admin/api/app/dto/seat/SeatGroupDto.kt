package dev.dornol.ticket.admin.api.app.dto.seat

import com.querydsl.core.annotations.QueryProjection

data class SeatGroupDto @QueryProjection constructor(
    val id: String,
    val name: String,
    val color: String,
    val displayOrder: Long,
    val seats: MutableList<SeatDto> = mutableListOf()
) {
    @QueryProjection
    constructor(id: Long, name: String, color: String, displayOrder: Long) : this(id.toString(), name, color, displayOrder)
}
