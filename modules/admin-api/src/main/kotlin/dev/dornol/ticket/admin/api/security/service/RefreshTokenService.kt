package dev.dornol.ticket.admin.api.security.service

import dev.dornol.ticket.admin.api.app.domain.auth.TokenBundle
import dev.dornol.ticket.admin.api.security.dto.TokenDto
import dev.dornol.ticket.admin.api.security.repository.AuthTokenRedisRepository
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

    fun popRefreshToken(refreshTokenValue: String): TokenBundle? {
        // TODO: Transaction 을 위해 RedisTemplate, GETDEL 사용을 검토
        return authTokenRedisRepository.findByRefreshTokenValue(refreshTokenValue)?.also {
            authTokenRedisRepository.delete(it)
        }
    }

}