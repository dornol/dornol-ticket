package dev.dornol.ticket.admin.api.security.authentication

import dev.dornol.ticket.admin.api.security.userdetails.AdminUser
import dev.dornol.ticket.admin.api.security.userdetails.AdminUserDetailsService
import org.springframework.context.MessageSource
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class AdminAuthenticationProvider(
    private val userDetailsService: AdminUserDetailsService,
    private val passwordEncoder: PasswordEncoder,
    private val messageSource: MessageSource
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val username = determineUsername(authentication)

        val user: AdminUser?

        try {
            user = userDetailsService.loadUserByUsername(username)
        } catch (e: UsernameNotFoundException) {
            throw BadCredentialsException(messageSource.getMessage("authentication.not-found", null, Locale.getDefault()))
        }

        if (!passwordEncoder.matches(authentication.credentials.toString(), user.password)) {
            throw BadCredentialsException(messageSource.getMessage("authentication.not-found", null, Locale.getDefault()))
        }

        if (!user.approved) {
            throw BadCredentialsException(messageSource.getMessage("authentication.not-approved", null, Locale.getDefault()))
        }

        user.eraseCredentials()

        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }

    private fun determineUsername(authentication: Authentication): String {
        return if (authentication.principal == null) "NONE_PROVIDED" else authentication.name
    }
}