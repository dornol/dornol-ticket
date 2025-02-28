package dev.dornol.ticket.admin.api.config.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import dev.dornol.ticket.admin.api.app.domain.auth.TokenResponse
import dev.dornol.ticket.admin.api.app.service.auth.RefreshTokenService
import dev.dornol.ticket.admin.api.app.service.auth.TokenGenerator
import dev.dornol.ticket.admin.api.config.security.userdetails.AdminUser
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class FormLoginAuthenticationSuccessHandler(
    private val accessTokenGenerator: TokenGenerator,
    private val refreshTokenService: RefreshTokenService,
    private val objectMapper: ObjectMapper,
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val accessToken = accessTokenGenerator.generateToken(authentication)

        val user = authentication.principal as AdminUser
        val refreshToken = refreshTokenService.generateRefreshToken(user.username, accessToken)

        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(response.writer, TokenResponse(accessToken, refreshToken))
    }
}