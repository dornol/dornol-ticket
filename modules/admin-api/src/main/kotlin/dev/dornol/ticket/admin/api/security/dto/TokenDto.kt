package dev.dornol.ticket.admin.api.security.dto

data class TokenDto(
    val value: String,
    val expiresIn: Long,
)
