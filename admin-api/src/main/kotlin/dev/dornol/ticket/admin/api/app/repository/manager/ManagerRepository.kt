package dev.dornol.ticket.admin.api.app.repository.manager

import dev.dornol.ticket.domain.entity.manager.Manager
import org.springframework.data.jpa.repository.JpaRepository

interface ManagerRepository : JpaRepository<Manager, Long> {

    fun findByUsername(username: String): Manager?

}