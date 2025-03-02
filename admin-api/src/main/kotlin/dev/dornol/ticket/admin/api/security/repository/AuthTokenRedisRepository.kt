package dev.dornol.ticket.admin.api.security.repository

import dev.dornol.ticket.admin.api.app.domain.auth.TokenBundle
import org.springframework.data.repository.CrudRepository

interface AuthTokenRedisRepository : CrudRepository<TokenBundle, String> {

    fun findByRefreshTokenValue(refreshTokenValue: String): TokenBundle?

}