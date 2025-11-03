package dev.dornol.ticket.manager.adapter.`in`.web.dto

import jakarta.validation.constraints.NotBlank

data class CheckUsernameDto(
    @field:NotBlank
    val username: String,
)
