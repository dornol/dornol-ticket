package dev.dornol.ticket.domain.entity.performance

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.site.SiteEntity
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Table(name = "performance_schedule")
@Entity
class PerformanceScheduleEntity(
    id: Long,
    performance: PerformanceEntity,
    site: SiteEntity,
    performanceDate: LocalDate,
    performanceTime: LocalTime
) : BaseEntity(id) {

    @Column(nullable = false)
    var performanceDate: LocalDate = performanceDate
        protected set

    @Column(nullable = false)
    var performanceTime: LocalTime = performanceTime
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id", nullable = false, updatable = false)
    val performance: PerformanceEntity = performance

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false, updatable = false)
    val site: SiteEntity = site

    fun edit(performanceDate: LocalDate, performanceTime: LocalTime) {
        this.performanceDate = performanceDate
        this.performanceTime = performanceTime
    }
}