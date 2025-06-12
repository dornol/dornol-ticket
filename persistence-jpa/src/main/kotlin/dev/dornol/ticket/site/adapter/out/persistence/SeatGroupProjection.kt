package dev.dornol.ticket.site.adapter.out.persistence

import com.querydsl.core.annotations.QueryProjection

data class SeatGroupProjection @QueryProjection constructor(
    val id: Long,
    val name: String,
    val color: String,
    val displayOrder: Long,
) {
}
