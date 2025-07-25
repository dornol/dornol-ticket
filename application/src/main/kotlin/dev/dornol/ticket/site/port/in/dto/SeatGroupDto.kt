package dev.dornol.ticket.site.port.`in`.dto

import dev.dornol.ticket.site.domain.SeatGroupId

data class SeatGroupDto(
    val id: SeatGroupId,
    val name: String,
    val color: String,
    val displayOrder: Long,
    val seats: MutableList<SeatDto> = mutableListOf()
)
