package dev.dornol.ticket.admin.api.app.dto.seat.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class SeatEditRequestDto(
    @field:NotBlank
    val name: String,
    @field:Min(0)
    val groupId: Long,
)