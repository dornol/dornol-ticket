package dev.dornol.ticket.admin.api.security.userdetails

import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

class AdminUser(
    val userId: Long,
    val name: String,
    private val username: String,
    password: String,
    approved: Boolean,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails, Serializable, CredentialsContainer {

    private var password: String? = password

    val approved: Boolean = approved

    override fun getAuthorities() = authorities

    override fun getPassword() = password

    override fun getUsername() = username

    override fun eraseCredentials() {
        this.password = null
    }

}
