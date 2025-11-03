package dev.dornol.ticket.performance.adapter.out.persistence

import dev.dornol.ticket.performance.adapter.out.jpa.PerformanceScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PerformanceScheduleEntityRepository : JpaRepository<PerformanceScheduleEntity, Long> {
}