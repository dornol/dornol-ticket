package dev.dornol.ticket.admin.api.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import dev.dornol.ticket.admin.api.security.dto.AccessTokenBundleDto
import dev.dornol.ticket.admin.api.security.dto.TokenBundleDto
import dev.dornol.ticket.admin.api.security.dto.TokenDto
import dev.dornol.ticket.admin.api.security.enums.RefreshTokenAcceptType
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component

@Component
class TokenResponseHandler(
    private val objectMapper: ObjectMapper,
) {

    companion object {
        const val REFRESH_TOKEN_COOKIE_NAME = "dtrt"
        const val REFRESH_TOKEN_ACCEPT_HEADER_NAME: String = "X-Refresh-Token-Accept"
    }

    fun responseToken(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessToken: TokenDto,
        refreshToken: TokenDto
    ) {
        val type = resolveRefreshTokenAcceptType(request)

        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        if (type == RefreshTokenAcceptType.COOKIE) {
            response.addCookie(makeRefreshTokenCookie(refreshToken))
            objectMapper.writeValue(
                response.writer,
                AccessTokenBundleDto(accessToken)
            )
        } else {
            objectMapper.writeValue(
                response.writer,
                TokenBundleDto(accessToken, refreshToken)
            )
        }
    }

    private fun resolveRefreshTokenAcceptType(request: HttpServletRequest) = request.getHeader(REFRESH_TOKEN_ACCEPT_HEADER_NAME)
        ?.takeIf { it == RefreshTokenAcceptType.COOKIE.name }
        ?.let { RefreshTokenAcceptType.COOKIE }
        ?: RefreshTokenAcceptType.BODY

    private fun makeRefreshTokenCookie(refreshToken: TokenDto): Cookie {

        val cookie = Cookie(REFRESH_TOKEN_COOKIE_NAME, refreshToken.value)
        cookie.isHttpOnly = true
        cookie.secure = true
        cookie.path = "/"
        cookie.maxAge = refreshToken.expiresIn.toInt()
        return cookie
    }

}