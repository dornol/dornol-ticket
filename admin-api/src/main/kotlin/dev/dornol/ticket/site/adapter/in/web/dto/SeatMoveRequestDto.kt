package dev.dornol.ticket.site.adapter.`in`.web.dto

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
