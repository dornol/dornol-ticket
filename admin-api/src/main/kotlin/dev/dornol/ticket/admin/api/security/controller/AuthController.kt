package dev.dornol.ticket.admin.api.security.controller

import dev.dornol.ticket.admin.api.security.dto.RefreshTokenRequestDto
import dev.dornol.ticket.admin.api.security.handler.TokenResponseHandler
import dev.dornol.ticket.admin.api.security.service.AccessTokenGenerator
import dev.dornol.ticket.admin.api.security.service.RefreshTokenService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*

@RequestMapping("/auth")
@RestController
class AuthController(
    private val refreshTokenService: RefreshTokenService,
    private val userDetailsService: UserDetailsService,
    private val accessTokenGenerator: AccessTokenGenerator,
    private val tokenResponseHandler: TokenResponseHandler,
) {

    @PostMapping("/refresh")
    fun refreshToken(
        @RequestBody refreshTokenRequest: RefreshTokenRequestDto,
        @CookieValue(name = TokenResponseHandler.REFRESH_TOKEN_COOKIE_NAME, required = false)
        refreshTokenInCookie: String?,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val refreshTokenValue = refreshTokenRequest.refreshToken
            ?: refreshTokenInCookie
            ?: throw BadCredentialsException("Refresh token is missing or invalid")

        val tokenBundle = refreshTokenService.popRefreshToken(refreshTokenValue)
            ?: throw BadCredentialsException("Refresh token not found")

        if (tokenBundle.accessTokenValue != refreshTokenRequest.accessToken) {
            throw BadCredentialsException("Access token does not match")
        }

        val userDetails = userDetailsService.loadUserByUsername(tokenBundle.username)

        val authentication = createAuthentication(userDetails)
        val accessToken = accessTokenGenerator.generateToken(authentication)
        val refreshToken = refreshTokenService.generateRefreshToken(userDetails.username, accessToken.value)

        tokenResponseHandler.responseToken(request, response, accessToken, refreshToken)
    }

    private fun createAuthentication(userDetails: UserDetails): UsernamePasswordAuthenticationToken {
        return UsernamePasswordAuthenticationToken(
            userDetails.username, null, userDetails.authorities
        )
    }

}