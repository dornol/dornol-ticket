package dev.dornol.ticket.performance.adapter.out.persistence

import dev.dornol.ticket.performance.adapter.out.jpa.PerformanceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PerformanceEntityRepository : JpaRepository<PerformanceEntity, Long> {
}