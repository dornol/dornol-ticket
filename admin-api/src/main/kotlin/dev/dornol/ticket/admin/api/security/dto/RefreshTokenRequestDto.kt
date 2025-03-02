package dev.dornol.ticket.admin.api.security.dto

data class RefreshTokenRequestDto(
    val accessToken: String,
    val refreshToken: String?
)