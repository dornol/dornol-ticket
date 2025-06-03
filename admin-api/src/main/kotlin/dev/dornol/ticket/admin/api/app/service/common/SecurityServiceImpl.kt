package dev.dornol.ticket.admin.api.app.service.common

import dev.dornol.ticket.admin.api.app.repository.manager.ManagerRepository
import dev.dornol.ticket.admin.api.config.exception.common.AccessDeniedException
import dev.dornol.ticket.admin.api.util.alive
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service

@Service
class SecurityServiceImpl(
    private val managerRepository: ManagerRepository
) : SecurityService {
    override fun getUserId() = getPrincipal().subject.toLong()

    override fun getCompanyId() = getPrincipal().claims["companyId"]?.toString()?.toLong() ?: throw AccessDeniedException()

    override fun assertUserId(userId: Long) {
        val jwt = getPrincipal()
        val manager = managerRepository.findByIdOrNull(jwt.subject.toLong())?.alive() ?: throw AccessDeniedException()
        assertAccess(manager.id == userId)
    }

    override fun assertCompanyId(companyId: Long) {
        val jwt = getPrincipal()
        val manager = managerRepository.findByIdOrNull(jwt.subject.toLong())?.alive() ?: throw AccessDeniedException()
        assertAccess(manager.company.id == companyId)
    }

    private fun getPrincipal(): Jwt {
        val auth: Authentication? = SecurityContextHolder.getContext().authentication
        if (auth == null || !auth.isAuthenticated) {
            throw AccessDeniedException()
        }

        return auth.principal as Jwt
    }

    private fun assertAccess(expression: Boolean) {
        if (!expression) {
            throw AccessDeniedException()
        }
    }
}