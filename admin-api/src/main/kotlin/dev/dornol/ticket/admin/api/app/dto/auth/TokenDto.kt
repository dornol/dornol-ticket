package dev.dornol.ticket.admin.api.app.dto.auth

data class TokenDto(
    val token: String,
    val expiresIn: Long,
)
