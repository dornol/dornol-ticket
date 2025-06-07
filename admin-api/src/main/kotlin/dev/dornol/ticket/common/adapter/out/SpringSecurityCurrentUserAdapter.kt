package dev.dornol.ticket.common.adapter.out

import dev.dornol.ticket.common.application.out.CurrentUserPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SpringSecurityCurrentUserAdapter : CurrentUserPort {

    override fun getCurrentUserId(): Long {
        return SecurityContextHolder.getContext().authentication.name.toLong()
    }

}
