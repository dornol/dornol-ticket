package dev.dornol.ticket.admin.api.app.service.auth

import dev.dornol.ticket.admin.api.app.dto.auth.TokenDto
import org.springframework.security.core.Authentication

interface TokenGenerator {

    fun generateToken(authentication: Authentication): TokenDto

}