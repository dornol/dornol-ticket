package dev.dornol.ticket.admin.api.security.dto

class TokenBundleDto(
    val userId: Long,
    val name: String,
    val username: String,
    val accessToken: TokenDto,
    val refreshToken: TokenDto
)
