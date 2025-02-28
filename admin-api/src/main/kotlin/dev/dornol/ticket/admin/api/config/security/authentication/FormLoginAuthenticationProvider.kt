package dev.dornol.ticket.admin.api.config.security.authentication

import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.admin.api.config.security.userdetails.AdminUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class FormLoginAuthenticationProvider(
    private val managerRepository: ManagerRepository,
    private val userDetailsService: UserDetailsService,
    passwordEncoder: PasswordEncoder
) : DaoAuthenticationProvider(passwordEncoder) {

    init {
        super.setUserDetailsService(userDetailsService)
    }

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.principal.toString()
        val userId = managerRepository.findIdByUsername(username) ?: throw UsernameNotFoundException("User $username not found")
        return super.authenticate(UsernamePasswordAuthenticationToken(userId, authentication.credentials))
    }

    override fun supports(authentication: Class<*>?) =
        authentication == UsernamePasswordAuthenticationToken::class.java
}