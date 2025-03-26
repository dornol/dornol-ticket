package dev.dornol.ticket.domain.entity.performance

import dev.dornol.ticket.domain.entity.BaseEntity
import dev.dornol.ticket.domain.entity.site.Site
import jakarta.persistence.*

@Entity
@Table(
    indexes = [Index(name = "idx_performance_site_domain", columnList = "performance_id, site_id")]
)
class PerformanceSite(
    performance: Performance,
    site: Site
) : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id", nullable = false, updatable = false)
    val performance: Performance = performance

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false, updatable = false)
    val site: Site = site

}