package dev.dornol.ticket.admin.api.app.repository.company

import dev.dornol.ticket.domain.entity.company.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Long> {
}