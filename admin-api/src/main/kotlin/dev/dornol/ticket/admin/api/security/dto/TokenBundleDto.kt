package dev.dornol.ticket.admin.api.security.dto

class TokenBundleDto(
    val accessToken: TokenDto,
    val refreshToken: TokenDto
)
