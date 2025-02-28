package dev.dornol.ticket.admin.api.app.service.auth

import dev.dornol.ticket.admin.api.app.domain.auth.TokenBundle
import dev.dornol.ticket.admin.api.app.dto.auth.TokenDto
import dev.dornol.ticket.admin.api.app.repository.auth.AuthTokenRedisRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RefreshTokenService(
    private val authTokenRedisRepository: AuthTokenRedisRepository,
    @Value("\${dornol.security.jwt.refresh-token.expiry-seconds}")
    private val expires: Long
) {

    fun generateRefreshToken(username: String, accessToken: String): TokenDto {
        val tokenBundle = TokenBundle(
            username = username,
            accessTokenValue = accessToken,
            expiresIn = expires,
        )
        authTokenRedisRepository.save(tokenBundle)
        return TokenDto(tokenBundle.refreshTokenValue, expires)
    }

    fun findRefreshToken(refreshTokenValue: String): TokenBundle? {
        return authTokenRedisRepository.findByRefreshTokenValue(refreshTokenValue)
    }

}