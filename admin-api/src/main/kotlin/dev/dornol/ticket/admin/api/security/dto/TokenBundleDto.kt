package dev.dornol.ticket.admin.api.security.dto

class TokenBundleDto(
    accessToken: TokenDto,
    val refreshToken: TokenDto
) : AccessTokenBundleDto(accessToken)
