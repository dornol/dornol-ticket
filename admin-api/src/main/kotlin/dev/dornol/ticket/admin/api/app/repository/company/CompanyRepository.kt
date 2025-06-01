package dev.dornol.ticket.admin.api.app.repository.company

import dev.dornol.ticket.domain.entity.manager.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<CompanyEntity, Long> {
}