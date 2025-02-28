package dev.dornol.ticket.admin.api.app.domain.auth

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
)
