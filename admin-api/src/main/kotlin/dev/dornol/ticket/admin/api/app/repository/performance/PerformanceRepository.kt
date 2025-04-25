package dev.dornol.ticket.admin.api.app.repository.performance

import dev.dornol.ticket.domain.entity.performance.Performance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PerformanceRepository : JpaRepository<Performance, Long>, PerformanceQueryRepository {
}