package dev.dornol.ticket.admin.api.app.repository.auth

import dev.dornol.ticket.admin.api.app.domain.auth.TokenBundle
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AuthTokenRedisRepository : CrudRepository<TokenBundle, UUID> {

    fun findByAccessToken(accessToken: String): TokenBundle?

}