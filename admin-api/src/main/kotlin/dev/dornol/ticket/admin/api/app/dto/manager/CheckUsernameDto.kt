package dev.dornol.ticket.admin.api.app.dto.manager

import jakarta.validation.constraints.NotBlank

data class CheckUsernameDto(
    @field:NotBlank
    val username: String,
)
