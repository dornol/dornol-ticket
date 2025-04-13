package dev.dornol.ticket.admin.api.app.dto.seat.request

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotNull

data class SeatMoveRequestDto(
    @field:NotNull
    @field:DecimalMin("0.0", inclusive = true)
    val x: Double,
    @field:NotNull
    @field:DecimalMin("0.0", inclusive = true)
    val y: Double,
)
