package dev.dornol.ticket.site.adapter.`in`.web.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class SeatEditRequestDto(
    @field:NotBlank
    val name: String,
    @field:Min(0)
    val groupId: Long,
)