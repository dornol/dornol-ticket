package dev.dornol.ticket.common.adapter.out

import dev.dornol.ticket.admin.api.config.exception.common.AccessDeniedException
import dev.dornol.ticket.common.application.out.CurrentUserPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service

@Service
class SpringSecurityCurrentUserAdapter : CurrentUserPort {

    override fun getCurrentUserId(): Long {
        return SecurityContextHolder.getContext().authentication.name.toLong()
    }

    override fun getCurrentUserCompanyId(): Long {
        return getJwt().getClaimAsString("companyId").toLong()
    }

    override fun matchCompanyId(id: Long) {
        val companyId = getCurrentUserCompanyId()
        if (id != companyId) {
            throw AccessDeniedException()
        }
    }

    private fun getJwt(): Jwt {
        val principal = SecurityContextHolder.getContext().authentication.principal
        if  (principal is Jwt) {
            return principal
        }
        throw IllegalStateException()
    }

}
