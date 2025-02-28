package dev.dornol.ticket.admin.api.app.domain.auth

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import java.util.UUID

@RedisHash("dornol-ticket:auth:refresh-token")
data class TokenBundle(
    @Id
    val refreshToken: String = UUID.randomUUID().toString(),
    @Indexed
    val username: String,
    val accessToken: String,
    @TimeToLive
    val expiration: Long
)