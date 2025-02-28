package dev.dornol.ticket.admin.api.app.service.auth

import dev.dornol.ticket.admin.api.app.domain.auth.TokenBundle
import dev.dornol.ticket.admin.api.app.repository.auth.AuthTokenRedisRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RefreshTokenService(
    private val authTokenRedisRepository: AuthTokenRedisRepository,
    @Value("\${dornol.security.jwt.refresh-token.expiry-seconds}")
    private val expires: Long
) {

    fun generateRefreshToken(username: String, accessToken: String): String {
        val tokenBundle = TokenBundle(
            username = username,
            accessToken = accessToken,
            expiration = expires,
        )
        authTokenRedisRepository.save(tokenBundle)
        return tokenBundle.refreshToken
    }

}