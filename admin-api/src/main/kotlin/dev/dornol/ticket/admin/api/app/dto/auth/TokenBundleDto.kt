package dev.dornol.ticket.admin.api.app.dto.auth

class TokenBundleDto(
    accessToken: TokenDto,
    val refreshToken: TokenDto
) : AccessTokenBundleDto(accessToken)
