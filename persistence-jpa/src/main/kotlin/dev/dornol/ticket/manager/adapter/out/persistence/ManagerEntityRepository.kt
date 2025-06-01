package dev.dornol.ticket.manager.adapter.out.persistence

import dev.dornol.ticket.manager.adapter.out.jpa.ManagerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ManagerEntityRepository : JpaRepository<ManagerEntity, Long> {
}