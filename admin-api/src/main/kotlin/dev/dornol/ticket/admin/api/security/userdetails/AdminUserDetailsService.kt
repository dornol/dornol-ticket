package dev.dornol.ticket.admin.api.security.userdetails

import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AdminUserDetailsService(
    private val managerRepository: ManagerRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val manager = managerRepository.findByIdOrNull(username.toLong()) ?: throw BadCredentialsException("")

        return AdminUser(
            userId = manager.id,
            password = manager.password,
            authorities = listOf(SimpleGrantedAuthority(manager.managerRole.name))
        )
    }

}