package dev.dornol.ticket.admin.api.security.filter

import dev.dornol.ticket.admin.api.app.service.auth.RefreshTokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.filter.OncePerRequestFilter

class TokenRefreshFilter(
    private val requestMatcher: RequestMatcher,
    private val refreshTokenService: RefreshTokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (requestMatcher.matches(request)) {

            request.getHeader(HttpHeaders.AUTHORIZATION)
                ?.replace("Bearer ", "")
                ?.takeIf { it.isNotBlank() }
                ?.let {}

            // TODO:

            return
        }

        doFilterInternal(request, response, filterChain)
    }



}