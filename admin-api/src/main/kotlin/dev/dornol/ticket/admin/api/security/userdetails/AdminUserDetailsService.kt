package dev.dornol.ticket.admin.api.security.userdetails

import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class AdminUserDetailsService(
    private val managerRepository: ManagerRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): AdminUser {
        val manager = managerRepository.findByUsername(username) ?: throw UsernameNotFoundException("User Not Found")

        return AdminUser(
            manager = manager,
            authorities = listOf(SimpleGrantedAuthority(manager.managerRole.name))
        )
    }

}