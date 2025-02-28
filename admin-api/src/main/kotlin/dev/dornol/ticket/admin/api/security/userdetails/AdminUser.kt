package dev.dornol.ticket.admin.api.security.userdetails

import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

class AdminUser(
    private val userId: Long,
    password: String,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails, Serializable, CredentialsContainer {

    private var password: String? = password

    override fun getAuthorities() = authorities

    override fun getPassword() = password

    override fun getUsername() = userId.toString()

    override fun eraseCredentials() {
        this.password = null
    }

}