package dev.dornol.ticket.admin.api.app.repository.performance

import dev.dornol.ticket.domain.entity.performance.PerformanceEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PerformanceRepository : JpaRepository<PerformanceEntity, Long>, PerformanceQueryRepository {
}