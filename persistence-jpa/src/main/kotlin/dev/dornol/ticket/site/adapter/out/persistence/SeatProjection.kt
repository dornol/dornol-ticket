package dev.dornol.ticket.site.adapter.out.persistence

import com.querydsl.core.annotations.QueryProjection
import dev.dornol.ticket.site.adapter.out.jpa.SeatOffsetEntity

data class SeatProjection @QueryProjection constructor(
    val id: Long,
    val name: String,
    val offset: SeatOffsetEntity,
    val displayOrder: Long,
    val groupId: Long
) {
}