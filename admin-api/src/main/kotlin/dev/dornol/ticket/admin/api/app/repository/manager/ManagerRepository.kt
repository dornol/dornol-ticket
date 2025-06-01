package dev.dornol.ticket.admin.api.app.repository.manager

import dev.dornol.ticket.domain.entity.manager.ManagerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ManagerRepository : JpaRepository<ManagerEntity, Long>, ManagerQueryRepository {

    fun findByUsername(username: String): ManagerEntity?

    fun existsByEmail(email: String): Boolean

}