package dev.dornol.ticket.admin.api.config.jpa

import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import java.util.*

@Component
class ManagerAuditor : AuditorAware<Long> {
    override fun getCurrentAuditor(): Optional<Long> {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null) {
            val jwt = authentication.principal
            if (jwt is Jwt) {
                return Optional.of(jwt.subject.toLong())
            }
        }
        return Optional.empty()
    }
}