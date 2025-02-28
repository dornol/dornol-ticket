package dev.dornol.ticket.admin.api.security.handler

import dev.dornol.ticket.admin.api.app.service.auth.RefreshTokenService
import dev.dornol.ticket.admin.api.app.service.auth.TokenGenerator
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class FormLoginAuthenticationSuccessHandler(
    private val accessTokenGenerator: TokenGenerator,
    private val refreshTokenService: RefreshTokenService,
    private val tokenResponseHandler: TokenResponseHandler
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val accessToken = accessTokenGenerator.generateToken(authentication)
        val refreshToken = refreshTokenService.generateRefreshToken(authentication.name, accessToken.token)

        tokenResponseHandler.responseToken(request, response, accessToken, refreshToken)
    }
}