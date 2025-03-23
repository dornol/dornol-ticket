package dev.dornol.ticket.admin.api.app.dto.auth

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import java.util.*

@RedisHash("dornol-ticket:auth:refresh-token")
data class TokenBundle(
    @Id
    val refreshTokenValue: String = UUID.randomUUID().toString(),

    val accessTokenValue: String,

    @Indexed
    val username: String,

    @TimeToLive
    val expiresIn: Long
)