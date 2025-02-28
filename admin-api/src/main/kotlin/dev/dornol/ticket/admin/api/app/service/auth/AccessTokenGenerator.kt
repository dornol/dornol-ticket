package dev.dornol.ticket.admin.api.app.service.auth

import dev.dornol.ticket.admin.api.app.dto.auth.TokenDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors

@Service
class AccessTokenGenerator(
    private val encoder: JwtEncoder,
    @Value("\${dornol.security.jwt.access-token.expiry-seconds:1800}")
    private val expires: Long,
) : TokenGenerator {

    override fun generateToken(authentication: Authentication): TokenDto {
        return this.generateToken(authentication.name, authentication.authorities)
    }

    private fun generateToken(username: String, authorities: Collection<GrantedAuthority>): TokenDto {
        val now = Instant.now()
        val scope = authorities.stream().map { it.authority }.collect(Collectors.joining(" "))
        val expiresAt = now.plus(expires, ChronoUnit.SECONDS)
        val claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(expiresAt)
            .subject(username)
            .claim("scope", scope)
            .build()

        val token = encoder.encode(JwtEncoderParameters.from(claims)).tokenValue
        return TokenDto(token, expires)
    }

}