package dev.dornol.ticket.admin.api.app.redisrepository.auth

import dev.dornol.ticket.admin.api.app.dto.auth.TokenBundle
import org.springframework.data.repository.CrudRepository

interface AuthTokenRedisRepository : CrudRepository<TokenBundle, String> {

    fun findByRefreshTokenValue(refreshTokenValue: String): TokenBundle?

}