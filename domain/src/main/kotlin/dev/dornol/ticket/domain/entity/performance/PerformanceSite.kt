package dev.dornol.ticket.domain.entity.performance

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.site.Site
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

private const val TABLE_NAME = "performance_site"
private const val PERFORMANCE_ID = "performance_id"
private const val SITE_ID = "site_id"

private const val PERFORMANCE_SITE_INDEX = "performance_site_index"

@Entity
@Table(
    name = TABLE_NAME,
    indexes = [Index(name = PERFORMANCE_SITE_INDEX, columnList = "$PERFORMANCE_ID, $SITE_ID")]
)
class PerformanceSite(
    performance: Performance,
    site: Site,
    performanceDate: LocalDate,
    performanceTime: LocalTime
) : BaseEntity() {

    @Column(nullable = false, updatable = false)
    val performanceDate: LocalDate = performanceDate

    @Column(nullable = false, updatable = false)
    val performanceTime: LocalTime = performanceTime

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PERFORMANCE_ID, nullable = false, updatable = false)
    val performance: Performance = performance

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = SITE_ID, nullable = false, updatable = false)
    val site: Site = site

}