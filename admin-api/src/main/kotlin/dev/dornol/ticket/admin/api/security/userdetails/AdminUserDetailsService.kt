package dev.dornol.ticket.admin.api.security.userdetails

import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class AdminUserDetailsService(
    private val managerRepository: ManagerRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): AdminUser {
        val manager = managerRepository.findByUsername(username) ?: throw UsernameNotFoundException("User Not Found")

        return AdminUser(
            userId = manager.id,
            name = manager.name,
            username = manager.username,
            password = manager.password,
            authorities = listOf(SimpleGrantedAuthority(manager.managerRole.name))
        )
    }

}