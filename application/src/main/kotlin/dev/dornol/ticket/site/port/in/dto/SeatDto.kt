package dev.dornol.ticket.site.port.`in`.dto

import dev.dornol.ticket.site.domain.SeatGroupId
import dev.dornol.ticket.site.domain.SeatId

data class SeatDto(
    val id: SeatId,
    val name: String,
    val offset: SeatOffsetDto,
    val displayOrder: Long,
    val groupId: SeatGroupId
)
