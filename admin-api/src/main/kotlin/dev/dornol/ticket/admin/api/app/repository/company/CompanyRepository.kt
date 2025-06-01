package dev.dornol.ticket.admin.api.app.repository.company

import dev.dornol.ticket.manager.adapter.out.jpa.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<CompanyEntity, Long> {
}