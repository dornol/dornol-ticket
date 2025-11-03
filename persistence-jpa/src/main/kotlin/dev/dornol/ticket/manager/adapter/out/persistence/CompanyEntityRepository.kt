package dev.dornol.ticket.manager.adapter.out.persistence

import dev.dornol.ticket.manager.adapter.out.jpa.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyEntityRepository : JpaRepository<CompanyEntity, Long> {
}