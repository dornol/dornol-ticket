package dev.dornol.ticket.admin.api.security.userdetails

import dev.dornol.ticket.domain.entity.manager.Manager
import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

class AdminUser(
    manager: Manager,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails, Serializable, CredentialsContainer {

    private val username: String = manager.username

    private var password: String? = manager.password

    val userId: Long = manager.id!!

    val name: String = manager.name

    val approved: Boolean = manager.approval.approved

    val companyId: Long = manager.company.id!!

    override fun getAuthorities() = authorities

    override fun getPassword() = password

    override fun getUsername() = username

    override fun eraseCredentials() {
        this.password = null
    }

}
