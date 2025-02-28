package dev.dornol.ticket.admin.api.app.service.auth

import org.springframework.security.core.Authentication

interface TokenGenerator {

    fun generateToken(authentication: Authentication): String

}