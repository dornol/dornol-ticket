package dev.dornol.ticket.admin.api.security.service

import dev.dornol.ticket.admin.api.security.dto.TokenDto
import org.springframework.security.core.Authentication

interface TokenGenerator {

    fun generateToken(authentication: Authentication): TokenDto

}