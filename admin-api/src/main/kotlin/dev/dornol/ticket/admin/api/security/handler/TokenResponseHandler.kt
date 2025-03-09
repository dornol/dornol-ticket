package dev.dornol.ticket.admin.api.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import dev.dornol.ticket.admin.api.security.dto.TokenBundleDto
import dev.dornol.ticket.admin.api.security.dto.TokenDto
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
    }

    fun responseToken(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessToken: TokenDto,
        refreshToken: TokenDto
    ) {
        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(
            response.writer,
            TokenBundleDto(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        )
    }

}